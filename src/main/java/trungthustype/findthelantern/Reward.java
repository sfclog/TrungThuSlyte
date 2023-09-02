package trungthustype.findthelantern;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import trungthustype.findthelantern.util.Color;

import java.util.ArrayList;
import java.util.List;

public class Reward {

    public static void give_reward(Player p) {
        String cmd = "bc &eChúc mừng ! &f" + p.getName() + " &2đã giành chiến thắng trong trờ chơi &6Tìm Kiếm Lồng Đèn &2và nhận về giải thưởng cho mình !";
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Color.tran(cmd));
        give_item_event(p,10);

    }

    public static void give_item_event(Player p , int i) {

        ItemStack item = new ItemStack(Material.LANTERN , i);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Color.tran("&e&lLồng Đèn Trung Thu"));
        List<String> lore = new ArrayList();
        lore.add(" ");
        lore.add(Color.tran("&7Lồng đèn trung thu được trao cho"));
        lore.add(Color.tran("&7những người đã giành chiến thắng"));
        lore.add(Color.tran("&7trong cuộc thi &eTìm Kiếm Lồng Đèn"));
        lore.add(Color.tran("&7được tổ chức vào &dTrung Thu 2023"));
        lore.add(" ");
        lore.add(Color.tran("&6Dùng để trao đổi hoặc mua bán"));
        lore.add(" ");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY,100,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);

        if(p.getInventory() != null) {
            if (!(p.getInventory().firstEmpty() == -1)) {
               p.getInventory().addItem(item);
            } else {
                p.getWorld().dropItem(p.getLocation(),item);
            }
        }

    }
}
