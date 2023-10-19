package org.siberianhusy.bluemapsetmarkers.utils;

public class Replace {
    //将字符串中的&替换为颜色符号
    public static String replaceColor(String string){
        return string.replace("&","§");
    }

    //将字符串中的[name]替换为name变量
    public static String replaceName(String string,String name){
        return replaceColor(string).replace("[name]",name);
    }
}
