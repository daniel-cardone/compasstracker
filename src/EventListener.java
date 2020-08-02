import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerSpawn(PlayerRespawnEvent spawn) {
        Player player = spawn.getPlayer();

        player.sendMessage("You died! Here's a new compass.");
        player.getInventory().addItem(new ItemStack(Material.COMPASS));
    }
}
