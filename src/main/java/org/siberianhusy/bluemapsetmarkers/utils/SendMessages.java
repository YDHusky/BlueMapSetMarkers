package org.siberianhusy.bluemapsetmarkers.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SendMessages {
    //发送信息(列表) -sender
    public static void sendMessagesList(CommandSender sender,String key){
        List<String> messages = Get.getMessagesList(key);
        for (String message : messages) {
            sender.sendMessage(Replace.replaceColor(message));
        }
    }
    //发送信息(列表) -player
    public static void sendMessagesList(Player player,String key){
        List<String> messages = Get.getMessagesList(key);
        for (String message : messages) {
            player.sendMessage(Replace.replaceColor(message));
        }
    }
    //发送信息(字符串) -sender
    public static void sendMessagesString(CommandSender sender,String key){
        sender.sendMessage(Get.getMessagesString(key));
    }
    //发送信息(字符串) -sender(替换[name])
    public static void sendMessagesString(CommandSender sender,String key,String name){
        sender.sendMessage(Get.getMessagesString(key,name));
    }

    //发送信息(字符串) -player
    public static void sendMessagesString(Player player,String key){
        player.sendMessage(Get.getMessagesString(key));
    }
    //发送信息(字符串) -player(替换[name])
    public static void sendMessagesString(Player player,String key,String name){
        player.sendMessage(Get.getMessagesString(key,name));
    }
    //发送标记点信息
    public static void sendMarkerInfo(String name,Player player){
        List<String> info =  Get.getMarkerInfo(name, player.getWorld());
        if (info!=null){
            for (String mes : info){
                player.sendMessage(Replace.replaceColor(mes));
            }
        }else {
            sendMessagesString(player,"Error.nameNotExist",name);
        }
    }
}
