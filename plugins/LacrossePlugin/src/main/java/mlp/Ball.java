package mlp;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftSnowball;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Ball extends ItemStack {

    static Material snowballMaterial = Material.SNOWBALL;

    public Ball() {
        super(snowballMaterial);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("Lacrosse Ball");
        this.setItemMeta(itemMeta);
    }

//    public boolean equals(Entity entity) {
//        if(entity.getMetadata())
//    }
}

