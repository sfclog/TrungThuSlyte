package trungthustype.findthelantern;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import trungthustype.findthelantern.util.LocationUtil;
import java.util.ArrayList;
import java.util.List;

public class PlayerDataManage {


    public static boolean add(Player p ,Location loc) {
        List<Location> l = getAllLoc(p);
        if(l != null) {
            if(!l.contains(loc)) {
                l.add(loc);
                setAllLoc(p,l);
                return true;
            }
        }
        return false;
    }

    public static boolean remove(Player p ,Location loc) {
        List<Location> l = getAllLoc(p);
        if(l != null) {
            if(l.contains(loc)) {
                l.remove(loc);
                setAllLoc(p,l);
                return true;
            }
        }
        return false;
    }

    public static boolean have(Player p , Location loc) {
        if(getAllLoc(p) != null && getAllLoc(p).contains(loc)) {
            return true;
        }
        return false;
    }


    public static void setAllLoc(Player p ,List<Location> l) {
        List<String> loc = new ArrayList<>();
        for(Location lo : l) {
            if(lo != null) {
                loc.add(LocationUtil.cove_location(lo));
                PlayerData.setforcearray(p.getName(), loc);
                PlayerData.save();
            }
        }
    }

    public static List<Location> getAllLoc(Player p) {
        List<Location> loc = new ArrayList<>();
        if(PlayerData.getarray(p.getName()) != null) {
            for (String s : PlayerData.getarray(p.getName())) {
                if (s != null) {
                    loc.add(LocationUtil.get_location_cover(s));
                }
            }
        }
        return loc;
    }
}
