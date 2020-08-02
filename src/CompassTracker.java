import org.bukkit.plugin.java.JavaPlugin;

public class CompassTracker extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("track").setExecutor(new TrackCommand(this));
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
