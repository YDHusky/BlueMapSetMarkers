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
import org.siberianhusy.bluemapsetmarkers.data.PlayerData;
import org.siberianhusy.bluemapsetmarkers.utils.Replace;
import org.siberianhusy.bluemapsetmarkers.utils.Util;

import java.util.List;
import java.util.Map;

public class PlayerGUI implements GUI{

    private final Inventory inv = Bukkit.createInventory(this, 6*9, "标记列表-玩家");
    private final Player player;
    private final World world;
    //todo 分页功能
    private int page;

    public PlayerGUI(Player player, World world) {
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
        int count=0;
        for (Map.Entry<String, Marker> entry : Data.worldMarkers.get(this.world).getMarkers().entrySet()){
            ItemMeta markerMeta = markerItem.getItemMeta();
            markerMeta.setDisplayName(entry.getKey());
            List<String> lore;
            lore = Util.getMarkerInfo(entry.getKey(), this.world);
            PlayerData playerData = Data.playerData.get(Util.getPlayerData(entry.getKey()));
            if (playerData.getPlayer().equals(this.player.getName())){
                if (lore != null) {
                    lore.add("&c右键删除该标记!");
                }
            }
            if (lore != null) {
                markerMeta.setLore(Replace.replaceColor(lore));
            }
            markerItem.setItemMeta(markerMeta);
            this.inv.setItem(count,markerItem);
            count++;
        }
    }

    public Inventory getInventory() {
        return inv;
    }
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
}
