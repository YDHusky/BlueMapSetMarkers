package org.siberianhusy.bluemapsetmarkers.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.siberianhusy.bluemapsetmarkers.BlueMapSetMarkers;
import org.siberianhusy.bluemapsetmarkers.utils.*;

import java.util.List;

public class AdminCommands implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        //玩家命令
        if(sender instanceof Player){
            Player player = (Player) sender;
            //玩家有权限时返回的操作
            if (player.hasPermission("bluemapsetmarkers.admin")){
                //管理员帮助
                if (args.length==0||(args.length==1&&args[0].equalsIgnoreCase("help"))){
                    SendMessages.sendMessagesList(sender,"adminHelp");
                    return true;
                }
                //当指令后参数长度为1时
                else if (args.length==1){
                    //管理员GUI
                    if (args[0].equalsIgnoreCase("gui")){
                        sender.sendMessage("&b开发中!");
                        return true;
                    }
                    //查看所有标记点名字
                    else if (args[0].equalsIgnoreCase("search")){
                        List<String> nameList = Get.getMarkersNameList();
                        StringBuilder message = new StringBuilder("&c现有的标记点:&9");
                        for (String mes : nameList){
                            message.append(mes).append(" ");
                        }
                        sender.sendMessage(Replace.replaceColor(message.toString()));
                        return true;
                    }
                    //重载插件
                    else if (args[0].equalsIgnoreCase("reload")){
                        LoadData.reloadConfig();
                        SendMessages.sendMessagesString(sender,"reload");
                        return true;
                    }
                    //否则返回帮助
                    else {
                        SendMessages.sendMessagesList(sender,"adminHelp");
                        return true;
                    }
                }
                //当指令后的参数有两个时
                else if (args.length==2) {
                    //删除标记点
                    if (args[0].equalsIgnoreCase("del")){
                        DelMarker.delMarkerAdmin(args[1],player.getWorld(),player);
                        return true;
                    }
                    //获取标记点信息
                    else if (args[0].equalsIgnoreCase("get")){
                        SendMessages.sendMarkerInfo(args[1],player);
                        return true;
                    }
                    //设置标记默认图标
                    else if (args[0].equalsIgnoreCase("set")){
                        BlueMapSetMarkers.plugin.getConfig().set("icon",args[1]);
                        LoadData.reloadConfig();
                        SendMessages.sendMessagesString(sender,"setIcon");
                        return true;
                    }
                    //返回帮助
                    else {
                        SendMessages.sendMessagesList(sender,"adminHelp");
                        return true;
                    }
                }
                else {
                    SendMessages.sendMessagesList(sender,"adminHelp");
                    return true;
                }

            }
            //无权限时返回的操作
            else {
                SendMessages.sendMessagesString(sender,"permission");
                return true;
            }
        }
        //控制台命令
        else {
            //管理员帮助
            if (args.length==0||(args.length==1&&args[0].equalsIgnoreCase("help"))){
                SendMessages.sendMessagesList(sender,"adminHelp");
                return true;
            }
            //插件重载
            else if (args[0].equalsIgnoreCase("reload")){
                LoadData.reloadConfig();
                SendMessages.sendMessagesString(sender,"reload");
                return true;
            }
            //其他指令需要在游戏中使用
            else {
                sender.sendMessage(Replace.replaceColor("&c其他指令需要在游戏中使用"));

                return true;
            }
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return TabList.returnList(strings,strings.length,commandSender);
    }
}
