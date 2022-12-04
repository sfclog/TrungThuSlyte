package trungthuslyte.trungthuslyte;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import trungthustype.findthelantern.LanternData;
import trungthustype.findthelantern.LanternDataManage;
import trungthustype.findthelantern.PlayerData;
import trungthustype.findthelantern.PlayerEvent;
import trungthustype.findthelantern.papi.PAPI;
import xyz.xenondevs.particle.ParticleEffect;

public final class TrungThuSlyte extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (final World w : Bukkit.getServer().getWorlds()) {
                        w.setTime(18000);
                }
                for(Location loc : LanternDataManage.getAllLoc()) {
                    if(loc != null) {
                        ParticleEffect.FLAME.display(loc.add(0.5,0.8,0.5));
                    }

                }
            }
        }, 10L, 10);

        LanternData.save();
        PlayerData.save();
        new PAPI().register();
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(),this);
    }

}
