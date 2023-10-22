package org.siberianhusy.bluemapsetmarkers.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.siberianhusy.bluemapsetmarkers.utils.Get;
import org.siberianhusy.bluemapsetmarkers.utils.Replace;

import java.util.List;

public class AdminGUI {
    public static void adminGUI(Player player,String titie,World world){
        Inventory adminGUI = Bukkit.createInventory(player,6*9,titie);
        //设置图标
        ItemStack markerItem = new ItemStack(Material.NAME_TAG);
        //设置计数器
        int count=0;
        //遍历世界标记
        List<String> markers = Get.getMarkerList(world);
        for (int i = 0; i < markers.size(); i++) {
            ItemMeta markerMeta = markerItem.getItemMeta();
            markerMeta.setDisplayName(markers.get(i));
            List<String> lore = Get.getMarkerInfo(markers.get(i),world);
            lore.add("&c左键传送到标记点");
            lore.add("&c右键删除标记点");
            lore = Replace.replaceColor(lore);
            markerMeta.setLore(lore);
            markerItem.setItemMeta(markerMeta);
            adminGUI.setItem(i,markerItem);
        }
        player.openInventory(adminGUI);
    }
}
