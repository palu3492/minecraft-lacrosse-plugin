package mlp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class AnimalEvents implements Listener {

    public AnimalEvents(MinecraftLacrosse plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
//        Bukkit.getLogger().info(
//                String.format("%s, %s, %s, %s", event.getCause(), event.getDamage(), event.getEntity(), event.getEntityType()));
        if(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK &&
                event.getDamager() instanceof Player &&
                ((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.IRON_SHOVEL) {
            event.setCancelled(true);
            Bukkit.getLogger().info("Hurting animal");
        }

    }
}