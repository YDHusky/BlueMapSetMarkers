package org.siberianhusy.bluemapsetmarkers.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.siberianhusy.bluemapsetmarkers.data.Data;

public class MapList {
    //地图列表GUI(玩家，GUI标题)
    public static void mapList(Player player,String title){
        //创建一个物品栏作为GUI
        Inventory mapList = Bukkit.createInventory(player,9,title);
        //设置地图的图标
        ItemStack worldItem = new ItemStack(Material.GRASS_BLOCK);
        //遍历世界
        for (int i = 0; i < Data.worldList.size(); i++) {
            ItemMeta worldMeta = worldItem.getItemMeta();
            worldMeta.setDisplayName(Data.worldList.get(i).getName());
            worldItem.setItemMeta(worldMeta);
            mapList.setItem(i,worldItem);
        }
        //打开地图列表
        player.openInventory(mapList);
    }
}
