package org.siberianhusy.bluemapsetmarkers.data;

import de.bluecolored.bluemap.api.markers.MarkerSet;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.siberianhusy.bluemapsetmarkers.BlueMapSetMarkers;
import org.siberianhusy.bluemapsetmarkers.utils.Get;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Data {

    //BlueMap WEB路径
    public static Path webRoot;

    //创建存储地图和MarkerSet的映射
    public static Map<World, MarkerSet> worldMarkers = new ConcurrentHashMap<>();

    //创建数据配置
    public static List<PlayerData> playerData = new ArrayList<>();
    public static File messagesFile = new File(BlueMapSetMarkers.plugin.getDataFolder(), "Messages/messages.yml");
    public static FileConfiguration messages = YamlConfiguration.loadConfiguration(messagesFile);

    //世界列表
    public static List<World> worldList = Get.getWorldList();


    public static File dataFile = new File(BlueMapSetMarkers.plugin.getDataFolder(), "Data/data.yml");
    public static FileConfiguration data = YamlConfiguration.loadConfiguration(dataFile);
}
