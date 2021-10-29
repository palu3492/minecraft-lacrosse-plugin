package mlp;

import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Horse;
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
import org.bukkit.util.Vector;


public class PlayerEvents implements Listener {

    static public Projectile ballProjectile;

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
        Ball ball = new Ball();
        Stick stick = new Stick();
        if(!player.getInventory().contains(ball)) {
            // Put a ball is the player's inventory
            // ItemStack snowballs = ball; // new mlp.Ball(Material.SNOWBALL);
//            snowballs.setAmount(1);
            player.getInventory().setItem(1, ball);
            player.sendMessage(ChatColor.BLUE + "You've been given a lacrosse ball");
        }
        if(!player.getInventory().contains(stick)) {
            player.getInventory().setItem(0, stick);
            player.sendMessage(ChatColor.BLUE + "You've been given a lacrosse stick");
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
            // Check if player has the ball
            if(event.getPlayer().getInventory().contains(new Ball())) {
                this.onPlayerThrow(event.getPlayer());
            }
        }
    }

    // Throw a ball if a player hits and animal while using their stick
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

        Bukkit.getLogger().info(String.format("%f", player.getEyeHeight()));

//        Location location = player.getLocation().add(0, 20, 0);
//        Vector projectileVector = location.getDirection();


        Horse horse = player.getWorld().spawn(player.getLocation().add(0, 0.5, 0), Horse.class);
        Projectile projectile = horse.launchProjectile(mlp.BallProjectile.class);
        horse.remove();

//        Projectile projectile = player.launchProjectile(mlp.BallProjectile.class);
        PlayerEvents.ballProjectile = projectile;

//        projectile.getLocation();
        Vector projectileVector = player.getLocation().getDirection();
        projectileVector.multiply(0.3);
        projectile.setVelocity(projectileVector);
//        Vector projectileVector = location;
//        projectileVector.setY(player.getEyeHeight());
//        projectileVector.multiply(1);
//        projectile.setY(getEyeHeight()
//        player.getLocation().add(0, p, 0);
//        projectile.setVelocity(projectileVector);
//        projectile.setGlowing(true);
//        Bukkit.getLogger().info("onPlayerThrow");
        Ball ball = new Ball();
        if(player.getInventory().contains(ball)) {
            //Bukkit.getLogger().info("Ball in inventory");
            //Bukkit.getLogger().info(String.format("%d", player.getInventory().first(ball)));
            int ballSlot = player.getInventory().first(ball);
//            if(ballSlot >= 0) {
//                player.getInventory().clear(ballSlot);
//            }
        }
    }


}
