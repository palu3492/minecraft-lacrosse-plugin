package mlp;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Stick extends ItemStack {

    static Material lacrosseBallMaterial = Material.IRON_SHOVEL;

    public Stick() {
        super(lacrosseBallMaterial);
        this.setAmount(1);
        ItemMeta itemMeta = this.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("Lacrosse Stick");
        this.setItemMeta(itemMeta);
    }

}
