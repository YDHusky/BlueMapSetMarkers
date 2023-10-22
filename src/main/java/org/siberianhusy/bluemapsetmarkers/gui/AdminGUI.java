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
import org.siberianhusy.bluemapsetmarkers.utils.Replace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminGUI {
    public static void adminGUI(Player player,String titie,World world){
        Inventory adminGUI = Bukkit.createInventory(player,6*9,titie);
        //设置图标
        ItemStack markerItem = new ItemStack(Material.NAME_TAG);
        //设置计数器
        int count=0;
        //遍历世界标记
        for (Map.Entry<String, Marker> entry : Data.worldMarkers.get(world).getMarkers().entrySet()){
            ItemMeta markerMeta = markerItem.getItemMeta();
            markerMeta.setDisplayName(entry.getKey());
            List<String> lore = Get.getMarkerInfo(entry.getKey(),world);
            lore.add(Replace.replaceColor("&c左键传送到标记点"));
            lore.add(Replace.replaceColor("&c右键删除标记点"));
            adminGUI.setItem(count,markerItem);
            count++;
        }
    }
}
