package trungthustype.findthelantern;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanternData {
    public static File locate = new File("plugins/TrungThuSlyte/", "data.yml");
    public static FileConfiguration DataFile = (FileConfiguration) YamlConfiguration.loadConfiguration(locate);




    public static List<String> getarray(String s) {
        FileConfiguration DataFile = (FileConfiguration) YamlConfiguration.loadConfiguration(locate);

        if (DataFile.contains(s)) {
            List<String> ss = new ArrayList<String>();
            for (String ok : DataFile.getStringList(s)) {
                ss.add(ChatColor.translateAlternateColorCodes('&', ok));
            }
            return ss;
        }
        return null;
    }

    public static int getint(String s) {
        FileConfiguration DataFile = (FileConfiguration) YamlConfiguration.loadConfiguration(locate);

        if (DataFile.contains(s)) {
            return DataFile.getInt(s);
        }
        return 0;
    }

    public static double getdoubl(String s) {

        FileConfiguration DataFile = (FileConfiguration) YamlConfiguration.loadConfiguration(locate);

        if (DataFile.contains(s)) {
            return DataFile.getDouble(s);
        }
        return 0;
    }

    public static boolean getb(String s) {
        FileConfiguration DataFile = (FileConfiguration) YamlConfiguration.loadConfiguration(locate);
        if (DataFile.contains(s)) {
            return DataFile.getBoolean(s);
        }
        return false;
    }

    public static String getlang(String s) {
        FileConfiguration DataFile = (FileConfiguration) YamlConfiguration.loadConfiguration(locate);
        if (DataFile.contains(s)) {
            return ChatColor.translateAlternateColorCodes('&', DataFile.getString(s));
        }
        return s;
    }

    public static void addlang(String s, double s2) {
        if (!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }

    public static void addlang(String s, Boolean s2) {
        if (!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }

    public static void addlang(String s, List<String> s2) {
        if (!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }

    public static void setforcelang(String s, String s2) {

        DataFile.set(s, s2);
    }

    public static void setforcelang(String s, double x) {
        DataFile.set(s, x);
    }


    public static void setforcearray(String s, List<String> x) {
        DataFile.set(s, x);
    }

    public static void addlang(String s, String s2) {
        if (!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }

    public static void addlang(String s, int s2) {
        if (!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }


    public static void save() {
        try {
            DataFile.save(locate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



