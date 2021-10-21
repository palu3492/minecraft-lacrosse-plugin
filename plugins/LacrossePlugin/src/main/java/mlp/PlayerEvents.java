package mlp;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

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
        this.broadcastMessageForNewPlayer(event, player);
        this.givePlayerABall(player);
    }

    private void givePlayerABall(Player player) {
        // If the player does not already have a ball
        if(!player.getInventory().contains(Material.SNOWBALL)) {
            // Put a ball is the player's inventory
            ItemStack snowballs = new ItemStack(Material.SNOWBALL);
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

    public void onPlayerInteract(PlayerInteractEvent event) {

    }


}
