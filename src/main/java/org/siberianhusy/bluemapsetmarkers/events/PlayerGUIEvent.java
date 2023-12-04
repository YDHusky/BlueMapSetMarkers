package org.siberianhusy.bluemapsetmarkers.events;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.siberianhusy.bluemapsetmarkers.data.Data;
import org.siberianhusy.bluemapsetmarkers.gui.MapListGUI;
import org.siberianhusy.bluemapsetmarkers.gui.PlayerGUI;
import org.siberianhusy.bluemapsetmarkers.utils.DelMarker;
import org.siberianhusy.bluemapsetmarkers.utils.Util;

import java.util.List;

public class PlayerGUIEvent implements Listener {
    private static World world;
    @EventHandler
    public static void playerClickGUI(InventoryClickEvent event){
        if(event.getInventory().getHolder()==null)
            return;
        if(event.getInventory().getHolder() instanceof MapListGUI || event.getWhoClicked().hasPermission("bluemapsetmarkers.player")) {
            //设置不能拖拽物品
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getRawSlot()<= Data.worldList.size()){
                world = Data.worldList.get(event.getRawSlot());
                new PlayerGUI(player,world).openInventory();
                //PGUI.playerGUI(player,"标记列表-玩家",world);
            }
        }
    }
    @EventHandler
    public static void playerMarkerGUIClick(InventoryClickEvent event){
        if(event.getInventory().getHolder()==null)
            return;
        if(event.getInventory().getHolder() instanceof PlayerGUI) {
            PlayerGUI gui = (PlayerGUI) event.getInventory().getHolder();
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            List<String> markerList = Util.getMarkerList(world);
            if (event.getRawSlot()<=markerList.size()){
                if (event.isRightClick()){
                    DelMarker.delMarker(markerList.get(event.getRawSlot()),world,player);
                    gui.ref();
                }
            }
        }
    }
}
