package trungthustype.findthelantern;

import org.bukkit.Location;
import trungthustype.findthelantern.util.LocationUtil;

import java.util.ArrayList;
import java.util.List;

public class LanternDataManage {


    public static boolean add(Location loc) {
        List<Location> l = getAllLoc();
        if(l != null) {
            if(!l.contains(loc)) {
                l.add(loc);
                setAllLoc(l);
                return true;
            }
        }
        return false;
    }

    public static boolean remove(Location loc) {
        List<Location> l = getAllLoc();
        if(l != null) {
            if(l.contains(loc)) {
                l.remove(loc);
                setAllLoc(l);
                return true;
            }
        }
        return false;
    }

    public static boolean have(Location loc) {
        if(getAllLoc() != null && getAllLoc().contains(loc)) {
            return true;
        }
        return false;
    }


    public static void setAllLoc(List<Location> l) {
        List<String> loc = new ArrayList<>();
        for(Location lo : l) {
            if(lo != null) {
                loc.add(LocationUtil.cove_location(lo));
                LanternData.setforcearray("Data", loc);
                LanternData.save();
            }
        }
    }

    public static List<Location> getAllLoc() {
        List<Location> loc = new ArrayList<>();
        if(LanternData.getarray("Data") != null) {
            for (String s : LanternData.getarray("Data")) {
                if (s != null) {
                    loc.add(LocationUtil.get_location_cover(s));
                }
            }
        }
        return loc;
    }
}
