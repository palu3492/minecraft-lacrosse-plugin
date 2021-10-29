package mlp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;


public class BallEvents implements Listener {

    public BallEvents(MinecraftLacrosse plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onProjectileHit(ProjectileHitEvent event) {
        Bukkit.getLogger().info(String.format("Type %s", event.getEntity()));
        if(event.getEntity() == mlp.PlayerEvents.ballProjectile) {
//            Bukkit.getLogger().info("Snowball hit");
//            event.setCancelled(true);
            // Remove the snowball before it shows break effect

            Vector velocity = event.getEntity().getVelocity();
            event.getEntity().remove();
            if(velocity.length() > 0.2) {
                Bukkit.getLogger().info(String.format("%f", velocity.length()));

                // Create a horse to shoot projectile
                Horse horse = event.getEntity().getWorld().spawn(event.getEntity().getLocation(), Horse.class);
                Projectile projectile = horse.launchProjectile(mlp.BallProjectile.class);
                horse.remove();

                Vector blockDirection = event.getHitBlockFace().getDirection();

                Vector ray = velocity.clone();
                Vector normal = blockDirection.clone();


                ray.subtract() 2



                // Find the direction of the block that was hit

//                blockDirection.normalize();

//                Vector vector = blockDirection.multiply(2.0 * blockDirection.dot(velocity)).add(blockDirection);

                Vector vector = velocity.subtract(blockDirection.multiply(2.0 * velocity.dot(blockDirection)));
                Double i = velocity.multiply(2.0).dot(blockDirection);
                Vector n = blockDirection.clone().normalize();
                n = n.multiply(n).
                i / n;

//                velocity.rotateAroundX(-90);
//                velocity.rotateAroundY(-90);
//                velocity.rotateAroundX(-90);
//                static Vec bounce(Vec n, Vec v) {
//                    Vec tmp = Vec.scalarMultiply(-2*Vec.dot(n,v), n);
//                    return Vec.add(tmp, v);
//                }
//
//                static double dot(Vec a, Vec b) {
//                    return a.x*b.x + a.y*b.y; // + a.z*b.z if you're in 3D
//                }


//                velocity = velocity.multiply(0);
//                velocity.setX(-velocity.getX());
//                velocity.setY(-velocity.getY());
//                velocity.setZ(-velocity.getZ());
                projectile.setVelocity(vector);

                PlayerEvents.ballProjectile = projectile; // Update the ball projectile
            } else {
                mlp.PlayerEvents.ballProjectile = null;
            }


//            Ball ball = new Ball();
//            event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ball);


        }
    }

}
