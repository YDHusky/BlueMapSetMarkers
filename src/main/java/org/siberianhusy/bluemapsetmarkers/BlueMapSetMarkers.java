package org.siberianhusy.bluemapsetmarkers;

import de.bluecolored.bluemap.api.BlueMapAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.siberianhusy.bluemapsetmarkers.commands.AdminCommands;
import org.siberianhusy.bluemapsetmarkers.commands.PlayerCommands;
import org.siberianhusy.bluemapsetmarkers.data.Data;
import org.siberianhusy.bluemapsetmarkers.events.AdminGUIEvent;
import org.siberianhusy.bluemapsetmarkers.events.PlayerGUIEvent;
import org.siberianhusy.bluemapsetmarkers.events.SignWatcher;
import org.siberianhusy.bluemapsetmarkers.utils.LoadData;

import java.util.Objects;

public class BlueMapSetMarkers extends JavaPlugin {
    public static BlueMapSetMarkers plugin;

    @Override
    public void onEnable() {
        // 插件启动执行
        plugin = this;
        this.saveResource("Data/data.yml",false);
        //创建标记数据储存文件
        LoadData.createFiles();
        //加载并注册标签和标记
        for (World world : Bukkit.getWorlds()) {
            LoadData.loadWorldMarkerSet(world);
            LoadData.registerWorld(world);
        }
        //获取BlueMap WEB路径
        BlueMapAPI.onEnable(api -> Data.webRoot = api.getWebApp().getWebRoot());
        //保存默认配置文件
        this.saveDefaultConfig();
        //保存语言文件
        this.saveResource("Messages/messages.yml",false);
        LoadData.loadPlayerData();
        //注册玩家指令
        Objects.requireNonNull(Bukkit.getPluginCommand("BlueMapSetMarkers")).setExecutor(new PlayerCommands());
        Objects.requireNonNull(Bukkit.getPluginCommand("BlueMapSerMarkersAdmin")).setExecutor(new AdminCommands());
        //注册自动补全
        Objects.requireNonNull(Bukkit.getPluginCommand("BlueMapSetMarkers")).setTabCompleter(new PlayerCommands());
        Objects.requireNonNull(Bukkit.getPluginCommand("BlueMapSerMarkersAdmin")).setTabCompleter(new AdminCommands());
        //注册事件
        Bukkit.getPluginManager().registerEvents(new PlayerGUIEvent(),this);
        Bukkit.getPluginManager().registerEvents(new AdminGUIEvent(),this);
        Bukkit.getPluginManager().registerEvents(new SignWatcher(),this);
        this.getLogger().info("BlueMapMarkers加载完成！欢迎使用！");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("开始保存数据！");
        for (World world : Bukkit.getWorlds()) LoadData.saveWorldMarkerSet(world);
        LoadData.savePlayerData();
        this.getLogger().info("BlueMapSetMarkers已卸载！感谢使用！");
    }
}
