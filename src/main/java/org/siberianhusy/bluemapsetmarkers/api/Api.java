package org.siberianhusy.bluemapsetmarkers.api;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import de.bluecolored.bluemap.api.markers.POIMarker;
import org.bukkit.Location;
import org.bukkit.World;
import org.siberianhusy.bluemapsetmarkers.data.Data;
import org.siberianhusy.bluemapsetmarkers.utils.Judge;

import java.awt.image.BufferedImage;

import static org.siberianhusy.bluemapsetmarkers.utils.Get.getBufferedImage;

public class Api {
    //删除标记点
    public static boolean delMarker(String name, World world){
        if (!Judge.judgeMarkerName(name)){
            return false;
        }else {
            Data.worldMarkers.get(world).getMarkers().remove(name);
            return true;
        }
    }

    //添加标记
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
}
