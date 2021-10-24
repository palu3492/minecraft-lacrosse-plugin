package mlp;

import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
//import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import mlp.Ball;

public class PlayerEvents implements Listener {

    public PlayerEvents(MinecraftLacrosse plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //////////////////////////////////////////////////////////////////////////
    // Server player events
    //////////////////////////////////////////////////////////////////////////

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Operations when a player joins the server
        Player player = event.getPlayer();

        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerPlayer sp = craftPlayer.getHandle();


        this.broadcastMessageForNewPlayer(event, player);
        this.givePlayerABall(player);
    }

    private void givePlayerABall(Player player) {
        // If the player does not already have a ball
        if(!player.getInventory().contains(new Ball(Material.SNOWBALL))) {
            // Put a ball is the player's inventory
            ItemStack snowballs = new Ball(Material.SNOWBALL);
            snowballs.setAmount(1);
            player.getInventory().addItem(snowballs);
            player.sendMessage(ChatColor.DARK_BLUE + "You've been given a lacrosse ball");
        }
    }

    private void broadcastMessageForNewPlayer(PlayerJoinEvent event, Player player) {
        // Broadcast message to server about player who just joined
        event.setJoinMessage("Hello " + ChatColor.GREEN + player.getName() + ChatColor.WHITE +  ", you fat little jew");
    }

    //////////////////////////////////////////////////////////////////////////
    // Player created events
    //////////////////////////////////////////////////////////////////////////

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
//        Bukkit.getLogger().info(String.format("%s, %s, %s", event.getAction(), event.hasItem(), event.getMaterial()));
        if((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) &&
                event.hasItem() && event.getMaterial() == Material.IRON_SHOVEL) {
            event.setCancelled(true);
            this.onPlayerThrow(event.getPlayer());
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
      if(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK &&
                event.getDamager() instanceof Player &&
                ((Player) event.getDamager()).getInventory().getItemInMainHand().getType() == Material.IRON_SHOVEL) {
            event.setCancelled(true);
            this.onPlayerThrow((Player) event.getDamager());
        }

    }

    //////////////////////////////////////////////////////////////////////////
    // Player actions
    //////////////////////////////////////////////////////////////////////////

//    private class LacrosseBall extends Material {
//
//    }

    private void onPlayerThrow(Player player) {
        Projectile projectile = player.launchProjectile(Snowball.class);
        projectile.setVelocity(projectile.getVelocity().multiply(1.5));
//        Bukkit.getLogger().info("onPlayerThrow");
    }


}
