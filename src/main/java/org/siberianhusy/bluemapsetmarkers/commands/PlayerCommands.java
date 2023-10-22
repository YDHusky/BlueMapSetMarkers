package org.siberianhusy.bluemapsetmarkers.commands;

import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.siberianhusy.bluemapsetmarkers.utils.*;

import java.util.List;

public class PlayerCommands implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        //帮助命令(当args长度为零或者args长度为1且值等于help时)
        if (args.length == 0||args.length==1&&args[0].equalsIgnoreCase("help")){
            SendMessages.sendMessagesList(sender,"help");
            return true;
        }
        //当命令发送者为玩家时执行
        if (sender instanceof Player){
            //将sender转换为player
            Player player = (Player) sender;
            //当指令后的参数只有一个时
            if (args.length==1){
                //添加标记点帮助
                if (args[0].equalsIgnoreCase("addhelp")){
                    SendMessages.sendMessagesList(sender,"addHelp");
                    return true;
                }
                //打开标记点GUI
                else if (args[0].equalsIgnoreCase("gui")) {
                    sender.sendMessage("&b开发中!");
                    return true;
                }
                //若参数不为以上两个则返回帮助列表
                else {
                    SendMessages.sendMessagesList(sender,"help");
                    return true;
                }
            }
            //当指令后参数有两个时
            else if (args.length==2) {
                //添加默认图标的标记
                if (args[0].equalsIgnoreCase("add")){
                    Location location = player.getLocation();
                    AddMarker.addMarker(location,args[1],Get.getConfigString("iconUrl"),player);
                    return true;
                }
                //删除标记
                else if (args[0].equalsIgnoreCase("del")) {
                    DelMarker.delMarker(args[1], player.getWorld(),player);
                    return true;
                }
                //若参数不符合则返回帮助列表
                else {
                    SendMessages.sendMessagesList(sender,"help");
                    return true;
                }
            }
            //当指令后参数长度为3时
            if (args.length==3){
                //添加有图标的地图标记
                if (args[0].equalsIgnoreCase("add")){
                    Location location = player.getLocation();
                    AddMarker.addMarker(location,args[1],args[2],player);
                    SendMessages.sendMessagesString(sender,"add",args[1]);
                    return true;
                }
                //否则返回帮助列表
                else {
                    SendMessages.sendMessagesList(sender,"help");
                    return true;
                }
            }
            //返回帮助列表
            else {
                SendMessages.sendMessagesList(sender,"help");
                return true;
            }
        }
        //当命令发送者不为玩家时执行
        else {
            SendMessages.sendMessagesString(sender,"Error.sender");
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return TabList.returnList(strings,strings.length,commandSender);
    }
}
