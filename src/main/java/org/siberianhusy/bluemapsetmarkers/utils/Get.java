package org.siberianhusy.bluemapsetmarkers.utils;

import com.flowpowered.math.vector.Vector3d;
import de.bluecolored.bluemap.api.markers.Marker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.siberianhusy.bluemapsetmarkers.BlueMapSetMarkers;
import org.siberianhusy.bluemapsetmarkers.data.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Get {
    //通过直链获取图片信息
    public static BufferedImage getBufferedImage(String imgUrl) {
        URL url;
        InputStream is = null;
        BufferedImage img = null;
        try {
            url = new URL(imgUrl);
            is = url.openStream();
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    //获取玩家Location
    public static Location playerGetLocation(Player player){
        return player.getLocation();
    }

    //获取messages。yml中的列表
    public static List<String> getMessagesList(String key){
        return Data.messages.getStringList(key);
    }
    //获取messages。yml中的字符串
    public static String getMessagesString(String key){
        return Replace.replaceColor(Objects.requireNonNull(Data.messages.getString(key)));
    }
    //获取messages。yml中的字符串
    //将字符串的[name]替换为name变量
    public static String getMessagesString(String key,String name){
        return Replace.replaceName(Data.messages.getString(key),name);
    }
    //获取config.yml中的字符串
    public static String getConfigString(String key){
        return BlueMapSetMarkers.plugin.getConfig().getString(key);
    }
    //获取世界中已经加载的标记
    public static List<String> getMarkersNameList(){
        List<String> markerName = new ArrayList<>();
        for (World world : Bukkit.getWorlds()){
            for (Map.Entry<String, Marker> entry : Data.worldMarkers.get(world).getMarkers().entrySet()){
                markerName.add(entry.getKey());
            }
        }
        return markerName;
    }
    public static List<String> getMarkerInfo(String name,World world){
        List<String> info = new ArrayList<>();
        int index = getPlayerData(name);
        if (index==-1){
            return null;
        }else {
            info.add("&b====&3" + name + "&b====");
            info.add("&9World:" + " &b" + world.getName());
            info.add("&9Player:"+" &b"+Data.playerData.get(index).getPlayer());
            Vector3d pos = Data.worldMarkers.get(world).getMarkers().get(name).getPosition();
            int x =(int)pos.getX();
            int y =(int)pos.getY();
            int z =(int)pos.getZ();
            info.add("&9X:" + " &b" + x);
            info.add("&9Y:" + " &b" + y);
            info.add("&9Z:" + " &b" + z);
            return info;
        }
    }
    public static int getPlayerData(String name) {
        for (int i = 0; i < Data.playerData.size(); i++) {
            if(Data.playerData.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
}
