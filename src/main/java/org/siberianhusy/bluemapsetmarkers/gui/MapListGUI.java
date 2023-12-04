package org.siberianhusy.bluemapsetmarkers.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.siberianhusy.bluemapsetmarkers.data.Data;

public class MapListGUI implements GUI {

    private final Inventory inv = Bukkit.createInventory(this, 6*9, "地图列表");
    private final Player player;

    public MapListGUI(Player player){
        this.player = player;
    }

    public void openInventory(){
        this.setItems();
        this.player.openInventory(inv);
    }

    public void ref(){
        this.inv.clear();
        this.setItems();
        this.player.openInventory(inv);
    }

    public void setItems(){
        ItemStack worldItem = new ItemStack(Material.GRASS_BLOCK);
        for (int i = 0; i < Data.worldList.size(); i++) {
            ItemMeta worldMeta = worldItem.getItemMeta();
            worldMeta.setDisplayName(Data.worldList.get(i).getName());
            worldItem.setItemMeta(worldMeta);
            this.inv.setItem(i,worldItem);
        }
    }

    public Inventory getInventory() {
        return inv;
    }
}
