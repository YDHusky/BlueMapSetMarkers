package org.siberianhusy.bluemapsetmarkers.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MapList {
    //地图列表GUI(玩家，GUI标题)
    public static void mapList(Player player,String title){
        //创建一个物品栏作为GUI
        Inventory mapList = Bukkit.createInventory(player,9,title);
        //设置地图的图标
        ItemStack worldItem = new ItemStack(Material.GRASS_BLOCK);
        //设置计数器
        int count = 0;
        //遍历世界
        for (World world : Bukkit.getWorlds()){
            //设置地图图标元数据
            ItemMeta worldMeta = worldItem.getItemMeta();
            worldMeta.setDisplayName(world.getName());
            worldItem.setItemMeta(worldMeta);
            mapList.setItem(count,worldItem);
            count++;
        }
        //打开地图列表
        player.openInventory(mapList);
    }
}
