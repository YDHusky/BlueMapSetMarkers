package org.siberianhusy.bluemapsetmarkers.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.siberianhusy.bluemapsetmarkers.utils.AddMarker;
import org.siberianhusy.bluemapsetmarkers.utils.Get;

import static org.siberianhusy.bluemapsetmarkers.BlueMapSetMarkers.plugin;

public class SignWatcher implements Listener {
    @EventHandler
    public void onSignWrite(SignChangeEvent event) {
        if(plugin.getConfig().getBoolean("isSignWatcher",true)){
            Component header = event.line(0);
            Block block = event.getBlock();
            Location location = block.getLocation();
            Player player = event.getPlayer();
            if (header == Component.empty() || header == null) return;
            if (!header.toString().contains("[map]")) return;
            Component clabel1 = event.line(1);
            if (clabel1 == Component.empty() || clabel1 == null) return;

            Component clabel2 = event.line(2);
            String label = LegacyComponentSerializer.legacySection().serialize(clabel1)
                    + LegacyComponentSerializer.legacySection().serialize(clabel2);

            Component cicon = event.line(3);
            if (cicon == Component.empty() || cicon == null) {
                AddMarker.addMarker(location,label,Get.getConfigString("iconUrl"),player);
                event.line(0, Component.empty());
                return;
            }
            String icon = LegacyComponentSerializer.legacySection().serialize(cicon);
            AddMarker.addMarker(location,label,icon,player);
            // Delete [map] and icon lines
            event.line(0, Component.empty());
            event.line(3, Component.empty());
        }
    }
}
