package org.siberianhusy.bluemapsetmarkers.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.siberianhusy.bluemapsetmarkers.utils.Replace;
import org.siberianhusy.bluemapsetmarkers.utils.Util;

import java.util.List;

public class AdminGUI implements GUI {

    private final Inventory inv = Bukkit.createInventory(this, 6*9, "标记列表-管理");
    private final Player player;
    private final World world;
    //todo 分页功能
    private int page;

    public AdminGUI(Player player, World world) {
        this.player = player;
        this.world = world;
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
        ItemStack markerItem = new ItemStack(Material.NAME_TAG);
        List<String> markers = Util.getMarkerList(this.world);
        for (int i = 0; i < markers.size(); i++) {
            ItemMeta markerMeta = markerItem.getItemMeta();
            markerMeta.setDisplayName(markers.get(i));
            List<String> lore = Util.getMarkerInfo(markers.get(i),this.world);
            lore.add("&c左键传送到标记点");
            lore.add("&c右键删除标记点");
            lore = Replace.replaceColor(lore);
            markerMeta.setLore(lore);
            markerItem.setItemMeta(markerMeta);
            this.inv.setItem(i,markerItem);
        }
    }

    public Inventory getInventory() {
        return inv;
    }
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
}
