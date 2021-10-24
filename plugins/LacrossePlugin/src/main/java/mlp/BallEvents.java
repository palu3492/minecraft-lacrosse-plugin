package mlp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class BallEvents implements Listener {

    public BallEvents(MinecraftLacrosse plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Bukkit.getLogger().info(String.format("Type %s", event.getEntityType()));
        if(event.getEntity() instanceof Snowball) {
            Bukkit.getLogger().info("Snowball hit");
        }
    }

}
