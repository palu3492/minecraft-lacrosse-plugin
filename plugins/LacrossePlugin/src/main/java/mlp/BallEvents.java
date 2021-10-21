package mlp;

import org.bukkit.event.Listener;

public class BallEvents implements Listener {

    public BallEvents(MinecraftLacrosse plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
