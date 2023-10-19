package org.siberianhusy.bluemapsetmarkers.utils;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import de.bluecolored.bluemap.api.markers.Marker;
import de.bluecolored.bluemap.api.markers.POIMarker;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.siberianhusy.bluemapsetmarkers.data.Data;
import org.siberianhusy.bluemapsetmarkers.data.PlayerData;

import java.awt.image.BufferedImage;

import static org.siberianhusy.bluemapsetmarkers.utils.Get.getBufferedImage;

public class AddMarker {
    //添加标记(对SWUSTMCTOWN提供)
    public static void addMarker(Location location, String name, String icon){
        Vector2i anchor;
        BufferedImage image = getBufferedImage(icon);
        int width = image.getWidth();
        int height = image.getHeight();
        anchor = new Vector2i(height/2, width/2);
        Vector3d pos = new Vector3d(location.getX(), location.getY(), location.getZ());
        POIMarker marker = POIMarker.builder().label(name).position(pos).maxDistance(100000).icon(icon,anchor).build();
        Data.worldMarkers.get(location.getWorld()).put(name, marker);
    }
    public static void addMarker(Location location,String name,String icon,Player player,Vector2i anchor){
        if (Judge.judgeMarkerName(name)){
            SendMessages.sendMessagesString(player,"Error.name",name);
            return;
        }
        Vector3d pos = new Vector3d(location.getX(), location.getY(), location.getZ());
        //创建标记
        POIMarker marker = POIMarker.builder().label(name).position(pos).maxDistance(100000).icon(icon,anchor).build();
        //添加到列表中
        Data.worldMarkers.get(location.getWorld()).put(name, marker);
        PlayerData playerData = new PlayerData();
        playerData.setPlayer(player.getName());
        playerData.setName(name);
        Data.playerData.add(playerData);
        SendMessages.sendMessagesString(player,"add",name);
        return;
    }
    public static boolean addMarker(Location location, String name, String icon, Player player){
        if (Judge.judgeMarkerName(name)){
            SendMessages.sendMessagesString(player,"Error.name",name);
            return false;
        }
        Vector2i anchor;
        //获取icon数据
        BufferedImage image = getBufferedImage(icon);
        int width = image.getWidth();
        int height = image.getHeight();
        anchor = new Vector2i(height/2, width/2);
        //创建位置信息
        Vector3d pos = new Vector3d(location.getX(), location.getY(), location.getZ());
        //创建标记
        POIMarker marker = POIMarker.builder().label(name).position(pos).maxDistance(100000).icon(icon,anchor).build();
        //添加到列表中
        Data.worldMarkers.get(location.getWorld()).put(name, marker);
        PlayerData playerData = new PlayerData();
        playerData.setPlayer(player.getName());
        playerData.setName(name);
        Data.playerData.add(playerData);
        SendMessages.sendMessagesString(player,"add",name);
        return true;
    }
}
