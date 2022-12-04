package trungthustype.findthelantern;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import trungthustype.findthelantern.util.Color;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.ArrayList;

public class PlayerEvent implements Listener {

    ArrayList<Player> adminmode = new ArrayList<Player>();

    @EventHandler
    public void player_place(BlockPlaceEvent e) {
     Player p = e.getPlayer();
        if (adminmode.contains(p)) {
            Block b = e.getBlock();
            if(b != null && b.getType() != null && b.getType() == Material.LANTERN) {
                if (LanternDataManage.add(b.getLocation())) {
                    p.sendMessage(Color.tran("&2Đã thêm lồng đèn này !"));
                } else {
                    p.sendMessage(Color.tran("&4Không thể thêm lồng đèn này !"));
                }
            }
        }
    }
    @EventHandler
    public void player_place(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!e.isCancelled()) {
          Block b = e.getBlock();
          if(b != null && b.getType() != null && b.getType() == Material.LANTERN) {
              if(LanternDataManage.remove(b.getLocation())) {
                  p.sendMessage(Color.tran("&2Đã xoá lồng đèn này !"));
              } else {
                  p.sendMessage(Color.tran("&4Không thể xoá lồng đèn này !"));
              }
          }
        }
    }

    @EventHandler
    public void click(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction() != null && !adminmode.contains(p)) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
               Block b = e.getClickedBlock();
               if(b != null && b.getType() != null && b.getType() == Material.LANTERN) {
                   if(LanternDataManage.have(b.getLocation())) {
                      if(!PlayerDataManage.have(p,b.getLocation())) {
                          PlayerDataManage.add(p, b.getLocation());
                          p.sendMessage(Color.tran("&f[&eTìm Kiếm Lồng Đèn&f] &aBạn đã lấy thành công lồng đèn này &f(&6" + PlayerDataManage.getAllLoc(p).size() + "/" + LanternDataManage.getAllLoc().size() + "&f) &a!"));
                          spawnFireworks(b.getLocation(),2);

                          if(PlayerDataManage.getAllLoc(p).size() == LanternDataManage.getAllLoc().size()) {
                              Reward.give_reward(p);
                          }

                      } else {
                          p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO,100,1);
                          ParticleEffect.BARRIER.display(b.getLocation().add(0.5,0.5,0.5));
                      }
                   }
               }
            }
        }
    }
    @EventHandler
    public void player_cmd(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (p.isOp() || p.hasPermission("*")) {
            if (e.getMessage().equals("/trungthustyle admin toggle")) {
                if (adminmode.contains(p)) {
                    adminmode.remove(p);
                    p.sendMessage(Color.tran("&4Đã tắt admin mode, bạn không thể chỉnh sửa lồng đèn !"));
                } else {
                    adminmode.add(p);
                    p.sendMessage(Color.tran("&2Đã bật admin mode, bạn có thể chỉnh sửa lồng đèn !"));
                }
                e.setCancelled(true);
            }
            if (e.getMessage().equals("/trungthustyle getitem")) {
                Reward.give_item_event(p,1);
                e.setCancelled(true);
            }
            if (e.getMessage().equals("/trungthustyle firework")) {
                for(Location loc : LanternDataManage.getAllLoc()) {
                    if (loc != null) {
                      spawnFireworks(loc,1);
                    }
                }
                e.setCancelled(true);
            }
            if (e.getMessage().equals("/trungthustyle delete")) {
                for(Location loc : LanternDataManage.getAllLoc()) {
                    if (loc != null) {
                       loc.getBlock().setType(Material.AIR);
                    }
                }
                e.setCancelled(true);
            }
            if (e.getMessage().contains("/trungthustyle check")) {

                String[] string = e.getMessage().split(" ");

                if(string.length == 3) {
                    Player pl = Bukkit.getPlayer(string[2]);
                    if(pl != null) {
                        p.sendMessage(Color.tran("&eList location !"));
                        for(Location loc : LanternDataManage.getAllLoc()) {
                            if(!PlayerDataManage.getAllLoc(pl).contains(loc)) {
                                p.sendMessage(loc.toString());
                            }
                        }
                    } else {
                        p.sendMessage(Color.tran("&3P IS OFF !"));
                    }
               } else {
                    p.sendMessage(Color.tran("&3Thiếu tên người chơi !"));
                }
                e.setCancelled(true);
            }
        }
    }

    public static void spawnFireworks(Location location, int amount){
        Location loc = location;
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        fwm.setPower(20);
        fwm.addEffect(FireworkEffect.builder().withColor(org.bukkit.Color.YELLOW).flicker(true).build());
        fw.setFireworkMeta(fwm);
        fw.detonate();
        for(int i = 0;i<amount; i++){
            Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            fw2.setFireworkMeta(fwm);
        }
    }
}
