package mlp;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;


public class MinecraftLacrosse extends JavaPlugin {

    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nMinecraft Lacrosse Plugin (MLP) enabled\n");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Setting up MLP\n");

        new PlayerEvents(this);
        new BallEvents(this);
        new ServerManagment(this);

        new AnimalEvents(this);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MLP set up!\n");
    }


}
