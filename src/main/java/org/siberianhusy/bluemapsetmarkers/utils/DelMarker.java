package org.siberianhusy.bluemapsetmarkers.utils;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.siberianhusy.bluemapsetmarkers.data.Data;


public class DelMarker {
    //删除标记点，对SWUSTMCTOWN提供
    public static boolean delMarker(String name, World world){
        if (!Judge.judgeMarkerName(name)){
            return false;
        }else {
            Data.worldMarkers.get(world).getMarkers().remove(name);
            return true;
        }
    }
    //删除标记点
    public static void delMarker(String name, World world,Player player){
        if (!Judge.judgeMarkerName(name)){
            SendMessages.sendMessagesString(player,"Error.nameNotExist",name);
        }else{
            //判断是否是玩家创建，如果是则删除并返回删除信息
            if (Judge.judgeCreatePlayer(name,player)) {
                Data.worldMarkers.get(world).getMarkers().remove(name);
                delPlayerData(name);
                SendMessages.sendMessagesString(player, "del", name);
            }
            //不是该玩家创建则返回错误信息
            else{
                SendMessages.sendMessagesString(player,"Error.player",name);
            }
        }
    }
    public static void delMarkerAdmin(String name, World world,Player player){
        if (!Judge.judgeMarkerName(name)){
            SendMessages.sendMessagesString(player,"Error.nameNotExist",name);
        }else{
            Data.worldMarkers.get(world).getMarkers().remove(name);
            delPlayerData(name);
            SendMessages.sendMessagesString(player, "del", name);
        }
    }

    public static void delPlayerData(String name) {
        for (int i = 0; i < Data.playerData.size(); i++) {
            if(Data.playerData.get(i).getName().equals(name)){
                Data.playerData.remove(i);
            }
        }
    }
}
