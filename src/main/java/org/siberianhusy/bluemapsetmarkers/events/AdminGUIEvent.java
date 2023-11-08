package org.siberianhusy.bluemapsetmarkers.events;

import com.flowpowered.math.vector.Vector3d;
import de.bluecolored.bluemap.api.markers.Marker;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.siberianhusy.bluemapsetmarkers.data.Data;
import org.siberianhusy.bluemapsetmarkers.gui.AdminGUI;
import org.siberianhusy.bluemapsetmarkers.utils.DelMarker;
import org.siberianhusy.bluemapsetmarkers.utils.Get;
import org.siberianhusy.bluemapsetmarkers.utils.SendMessages;

import java.util.List;

public class AdminGUIEvent implements Listener {
    private static World world = null;
    @EventHandler
    public static void adminClick(InventoryClickEvent event){
        if (event.getWhoClicked().getOpenInventory().getTitle().equals("地图列表-管理")){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getRawSlot()<= Data.worldList.size()){
                world = Data.worldList.get(event.getRawSlot());
                AdminGUI.adminGUI(player,"标记列表-管理",world);
            }
        }
    }
    @EventHandler
    public static void adminMarkerGUIClick(InventoryClickEvent event){
        if (event.getWhoClicked().getOpenInventory().getTitle().equals("标记列表-管理")){
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            List<String> markers = Get.getMarkerList(world);
            if (event.getRawSlot()<= markers.size()){
                Marker marker = Get.getMarker(world,markers.get(event.getRawSlot()));
                if (event.isLeftClick()){
                    Vector3d pos ;
                    if (marker!=null){
                        pos = marker.getPosition();
                        Location location = new Location(world,pos.getX(),pos.getY(),pos.getZ());
                        player.teleport(location);
                        SendMessages.sendMessagesString(player,"teleport",markers.get(event.getRawSlot()));
                    }
                }
                else if (event.isRightClick()){
                    if (marker!=null){
                        DelMarker.delMarkerAdmin(markers.get(event.getRawSlot()),world,player);
                        AdminGUI.adminGUI(player,"标记列表-管理",world);
                    }
                }
            }
        }
    }
}
