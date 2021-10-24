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

//    public enum CustomEntityType {
//
//        SKELETON("Skeleton", 51, EntityType.SKELETON, EntitySkeleton.class, CustomEntitySkeleton.class),
//
//        private String name;
//        private int id;
//        private EntityType entityType;
//        private Class<? extends EntityInsentient> nmsClass;
//        private Class<? extends EntityInsentient> customClass;
//
//        private CustomEntityType(String name, int id, EntityType entityType, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass){
//            this.name = name;
//            this.id = id;
//            this.entityType = entityType;
//            this.nmsClass = nmsClass;
//            this.customClass = customClass;
//        }
//
//
//
//        public String getName(){
//            return this.name;
//        }
//
//        public int getID(){
//            return this.id;
//        }
//
//        public EntityType getEntityType(){
//            return this.entityType;
//        }
//
//        public Class<? extends EntityInsentient> getNMSClass(){
//            return this.nmsClass;
//        }
//
//        public Class<? extends EntityInsentient> getCustomClass(){
//            return this.customClass;
//        }
//
//        }



}
