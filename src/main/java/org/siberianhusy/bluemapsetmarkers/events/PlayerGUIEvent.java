package org.siberianhusy.bluemapsetmarkers.events;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.siberianhusy.bluemapsetmarkers.data.Data;
import org.siberianhusy.bluemapsetmarkers.gui.PlayerGUI;
import org.siberianhusy.bluemapsetmarkers.utils.DelMarker;
import org.siberianhusy.bluemapsetmarkers.utils.Get;

import java.util.List;

public class PlayerGUIEvent implements Listener {
    private static World world;
    @EventHandler
    public static void playerClickGUI(InventoryClickEvent event){
        if (event.getWhoClicked().getOpenInventory().getTitle().equals("地图列表-玩家")){
            //设置不能拖拽物品
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getRawSlot()<= Data.worldList.size()){
                world = Data.worldList.get(event.getRawSlot());
                PlayerGUI.playerGUI(player,"标记列表-玩家",world);
            }
        }
    }
    @EventHandler
    public static void playerMarkerGUIClick(InventoryClickEvent event){
        if (event.getWhoClicked().getOpenInventory().getTitle().equals("标记列表-玩家")){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            List<String> markerList = Get.getMarkerList(world);
            if (event.getRawSlot()<=markerList.size()){
                if (event.isRightClick()){
                    DelMarker.delMarker(markerList.get(event.getRawSlot()),world,player);
                    PlayerGUI.playerGUI(player,"标记列表-玩家",world);
                }
            }
        }
    }
}
