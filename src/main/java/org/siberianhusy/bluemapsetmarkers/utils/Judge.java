package org.siberianhusy.bluemapsetmarkers.utils;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.siberianhusy.bluemapsetmarkers.data.Data;
import org.siberianhusy.bluemapsetmarkers.data.PlayerData;

import java.util.List;

public class Judge {
    //判断标签名字是否存在，若存在则返回true
    protected static boolean judgeMarkerName(String name){
        List<String> MarkersName = Get.getMarkersNameList();
        for (String s : MarkersName) {
            if (s.equals(name)) {
                return true;
            }
        }
        return false;
    }
    //判断标记是否是玩家创建,如果是则返回true
    public static boolean judgeCreatePlayer(String name, Player player){
        for (PlayerData playerData : Data.playerData){
            if (playerData.getPlayer().equals(player.getName())&&playerData.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
