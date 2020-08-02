import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class TrackCommand implements CommandExecutor {
    Plugin plugin;
    HashMap<String, Integer> tasks = new HashMap<>();

    public TrackCommand(Plugin p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command!");
            return false;
        }

        Player player = (Player)sender;
        if (args.length < 1) {
            return false;
        }

        String username = player.getName();
        if (tasks.containsKey(username)) {
            cancelTracker(username, tasks.get(username));
        }

        Player target;
        target = player.getServer().getPlayer(args[0]);

        if (target == null) {
            player.sendMessage("Please specify a valid player to track!");
            return false;
        }

        startTracker(player, target, 5);
        sender.sendMessage("Now tracking " + args[0]);

        return true;
    }

    public void startTracker(Player player, Player target, long ticks) {
        int taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,
                () -> player.setCompassTarget(target.getLocation()), 0L, ticks);
        tasks.put(player.getName(), taskID);
    }

    public void cancelTracker(String key, int taskID) {
        tasks.remove(key);
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
