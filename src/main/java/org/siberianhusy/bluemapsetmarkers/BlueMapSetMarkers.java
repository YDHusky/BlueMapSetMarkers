package org.siberianhusy.bluemapsetmarkers;

import de.bluecolored.bluemap.api.BlueMapAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.siberianhusy.bluemapsetmarkers.commands.AdminCommands;
import org.siberianhusy.bluemapsetmarkers.commands.PlayerCommands;
import org.siberianhusy.bluemapsetmarkers.data.Data;

import static org.siberianhusy.bluemapsetmarkers.utils.LoadData.*;

public class BlueMapSetMarkers extends JavaPlugin {
    public static BlueMapSetMarkers bmsm;

    @Override
    public void onEnable() {
        // 插件启动执行
        bmsm = this;
        this.saveResource("Data/data.yml",false);
        //创建标记数据储存文件
        createFiles();
        //加载并注册标签和标记
        for (World world : Bukkit.getWorlds()) {
            loadWorldMarkerSet(world);
            registerWorld(world);
        }
        //获取BlueMap WEB路径
        BlueMapAPI.onEnable(api -> Data.webRoot = api.getWebApp().getWebRoot());
        //保存默认配置文件
        this.saveDefaultConfig();
        //保存语言文件
        this.saveResource("Messages/messages.yml",false);
        loadPlayerData();
        //注册玩家指令
        Bukkit.getPluginCommand("BlueMapSetMarkers").setExecutor(new PlayerCommands());
        Bukkit.getPluginCommand("BlueMapSerMarkersAdmin").setExecutor(new AdminCommands());
        this.getLogger().info("BlueMapMarkers加载完成！欢迎使用！");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("开始保存数据！");
        // 插件卸载时执行
        for (World world : Bukkit.getWorlds()) saveWorldMarkerSet(world);
        savePlayerData();
        this.getLogger().info("BlueMapSetMarkers已卸载！感谢使用！");
    }
}