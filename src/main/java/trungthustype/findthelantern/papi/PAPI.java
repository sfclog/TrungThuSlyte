package trungthustype.findthelantern.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import trungthustype.findthelantern.LanternDataManage;
import trungthustype.findthelantern.PlayerDataManage;

public class PAPI extends PlaceholderExpansion {

    public String onPlaceholderRequest(Player p, String identifier) {
        if (identifier.equals("score")) {
            return String.valueOf(PlayerDataManage.getAllLoc(p).size());
        }
        if (identifier.equals("maxscore")) {
         return String.valueOf(LanternDataManage.getAllLoc().size());
        }
        return "0";
    }
    /*
    The identifier, shouldn't contain any _ or %
     */
    public String getIdentifier() {
        return "timkiemlongden";
    }
    /*
     The author of the Placeholder
     This cannot be null
     */
    public String getAuthor() {
        return "SFC_Log";
    }
    /*
     Same with #getAuthor() but for versioon
     This cannot be null
     */
    public String getVersion() {
        return "";
    }

}