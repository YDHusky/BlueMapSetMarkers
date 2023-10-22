package org.siberianhusy.bluemapsetmarkers.utils;

import java.util.ArrayList;
import java.util.List;

public class Replace {
    //将字符串中的&替换为颜色符号
    public static String replaceColor(String string){
        return string.replace("&","§");
    }

    //将字符串中的[name]替换为name变量
    public static String replaceName(String string,String name){
        return replaceColor(string).replace("[name]",name);
    }

    public static List<String> replaceColor(List<String> list){
        List<String> list1 = new ArrayList<>();
        for (String s : list){
            list1.add(replaceColor(s));
        }
        return list1;
    }
}
