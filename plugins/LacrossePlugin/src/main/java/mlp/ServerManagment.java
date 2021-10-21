package mlp;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ServerManagment implements Listener {

    public ServerManagment(MinecraftLacrosse plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.createSetTimeToDayTask(plugin);
    }

    private void createSetTimeToDayTask(MinecraftLacrosse plugin) {
        ServerManagment.SetTimeToDayTask task = new ServerManagment.SetTimeToDayTask(this);
        plugin.getServer().getScheduler().runTaskTimer(plugin, task, 1, 1000);
    }

    private static class SetTimeToDayTask implements Runnable {

        private final ServerManagment context;

        public SetTimeToDayTask(ServerManagment context) {
            this.context = context;
        }

        public void run() {
            try {
                context.setTimeToDay();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setTimeToDay() {
        for(World world : Bukkit.getWorlds()) {
            world.setFullTime(1050);
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.setCancelled(e.toWeatherState());
    }

}
