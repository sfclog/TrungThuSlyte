package trungthustype.findthelantern.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {

	public static String cove_location(Location loc) {
		String lo = loc.getWorld().getName()+ "," + loc.getX() + "," + loc.getY() + "," + loc.getZ();
		return lo;
	}

	public static Location get_location_cover(String ok) {
		String[] s = ok.split(",");
		Location loc = new Location(Bukkit.getWorld(s[0]), Double.parseDouble(s[1]) , Double.parseDouble(s[2]) , Double.parseDouble(s[3]));
		return loc;
	}

	
}
