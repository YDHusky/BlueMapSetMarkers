package org.siberianhusy.bluemapsetmarkers.utils;

import de.bluecolored.bluemap.api.BlueMapMap;
import de.bluecolored.bluemap.api.gson.MarkerGson;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.siberianhusy.bluemapsetmarkers.BlueMapSetMarkers;
import org.siberianhusy.bluemapsetmarkers.data.Data;
import org.siberianhusy.bluemapsetmarkers.data.PlayerData;

import java.io.*;


public class LoadData {
    //保存玩家数据
    public static void savePlayerData(){
        BlueMapSetMarkers.plugin.saveResource("Data/data.yml",true);
        File dataFile = new File(BlueMapSetMarkers.plugin.getDataFolder(), "Data/data.yml");
        FileConfiguration data = YamlConfiguration.loadConfiguration(dataFile);
        for (PlayerData playerData : Data.playerData){
            data.set(playerData.getName(),playerData.getPlayer());
        }
        try {
            data.save(dataFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //重载配置
    public static void reloadConfig(){
        BlueMapSetMarkers.plugin.reloadConfig();
        File messagesFile = new File(BlueMapSetMarkers.plugin.getDataFolder(), "Messages/messages.yml");
        Data.messages = YamlConfiguration.loadConfiguration(messagesFile);
    }
    //保存地图标记数据
    public static void saveWorldMarkerSet(World world) {
        //文件名字
        String name = "Data/marker-set-" + world.getName() + ".json";
        File file = new File(BlueMapSetMarkers.plugin.getDataFolder(), name);
        try (FileWriter writer = new FileWriter(file)) {
            MarkerGson.INSTANCE.toJson(Data.worldMarkers.get(world), writer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //当插件文件夹中不存在文件创建文件来保存
    public static void createFiles() {
        //获取服务器中地图，并遍历
        for (World world : Bukkit.getWorlds()) {
            String name = "Data/marker-set-" + world.getName() + ".json";
            createFile(name);
        }
    }
    //创建json文件
    private static void createFile(String data) {
        File dataFile = new File(BlueMapSetMarkers.plugin.getDataFolder(), data);
        try {
            //当文件夹中不存在时创建文件
            File folder = BlueMapSetMarkers.plugin.getDataFolder();
            if (!folder.exists()) folder.mkdirs();
            if (!dataFile.exists()) dataFile.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //加载文件中的标记数据
    public static void loadWorldMarkerSet(World world) {
        String name = "Data/marker-set-" + world.getName() + ".json";
        File file = new File(BlueMapSetMarkers.plugin.getDataFolder(), name);
        try (FileReader reader = new FileReader(file)) {
            MarkerSet set = MarkerGson.INSTANCE.fromJson(reader, MarkerSet.class);
            if (set != null) Data.worldMarkers.put(world, set);
        } catch (FileNotFoundException ignored) {
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //加载玩家数据
    public static void loadPlayerData(){
        for (String name:Data.data.getKeys(false)){
            PlayerData playerData = new PlayerData();
            playerData.setName(name);
            playerData.setPlayer(Data.data.getString(name));
            Data.playerData.add(playerData);
            System.out.println(name+" "+playerData.getPlayer());
        }
    }
    //注册标记标签
    public static void registerWorld(World world) {
        de.bluecolored.bluemap.api.BlueMapAPI.onEnable(api ->
                api.getWorld(world).ifPresent(blueWorld -> {
                    for (BlueMapMap map : blueWorld.getMaps()) {
                        String label = world.getName()+"-长期";
                        MarkerSet set = Data.worldMarkers.get(world);
                        if (set == null) set = MarkerSet.builder().label(label).build();
                        map.getMarkerSets().put(label, set);
                        Data.worldMarkers.put(world, set);
                    }
                })
        );
    }

}
