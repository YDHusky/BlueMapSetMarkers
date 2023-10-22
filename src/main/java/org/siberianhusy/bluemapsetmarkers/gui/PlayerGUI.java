package org.siberianhusy.bluemapsetmarkers.gui;

import de.bluecolored.bluemap.api.markers.Marker;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.siberianhusy.bluemapsetmarkers.data.Data;
import org.siberianhusy.bluemapsetmarkers.utils.Get;

import java.util.Map;

public class PlayerGUI {
    //玩家标记GUI
    public static void playerGUI(Player player,String title,World world){
        //创建GUI
        Inventory playerGUI = Bukkit.createInventory(player,6*9,title);
        //设置图标
        ItemStack markerItem = new ItemStack(Material.NAME_TAG);
        //设置计数器
        int count=0;
        //遍历世界标记
        for (Map.Entry<String, Marker> entry : Data.worldMarkers.get(world).getMarkers().entrySet()){
            ItemMeta markerMeta = markerItem.getItemMeta();
            markerMeta.setDisplayName(entry.getKey());
            markerMeta.setLore(Get.getMarkerInfo(entry.getKey(),world));
            playerGUI.setItem(count,markerItem);
            count++;
        }
        //打开GUI
        player.openInventory(playerGUI);
    }
}
