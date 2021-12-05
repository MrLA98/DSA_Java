package com.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// 集合覆盖问题
public class Broadcast {
    public static void main(String[] args) {
        HashSet<String> allCities = new HashSet<String>();
        allCities.add("北京");
        allCities.add("上海");
        allCities.add("天津");
        allCities.add("杭州");
        allCities.add("广州");
        allCities.add("深圳");
        allCities.add("成都");
        allCities.add("大连");

        HashMap<String, HashSet<String>> broadcast = new HashMap<String, HashSet<String>>();
        HashSet<String> set1 = new HashSet<String>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");
        HashSet<String> set2 = new HashSet<String>();
        set2.add("北京");
        set2.add("广州");
        set2.add("深圳");
        HashSet<String> set3 = new HashSet<String>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");
        HashSet<String> set4 = new HashSet<String>();
        set4.add("上海");
        set4.add("天津");
        HashSet<String> set5 = new HashSet<String>();
        set5.add("杭州");
        set5.add("大连");

        broadcast.put("K1", set1);
        broadcast.put("K2", set2);
        broadcast.put("K3", set3);
        broadcast.put("K4", set4);
        broadcast.put("K5", set5);

        ArrayList<String> res = new ArrayList<String>();
        while(allCities.size() != 0){
            HashSet<String> crossSet = new HashSet<String>();
            int cover = 0;
            String select = "";
            for(String key : broadcast.keySet()){
                HashSet<String> set = broadcast.get(key);
                HashSet<String> tempSet = new HashSet<String>();
                int temp = cross(set, allCities, tempSet);
                if(temp > cover){
                    cover = temp;
                    crossSet = tempSet;
                    select = key;
                }
            }
            res.add(select);
            delCross(allCities, crossSet);
        }
        System.out.println(res);
    }

    // 求交集
    public static int cross(HashSet<String> set, HashSet<String> all,HashSet<String> cross){
        int cover = 0;
        for(String item : set){
            if(all.contains(item)){
                ++cover;
                cross.add(item);
            }
        }
        return cover;
    }

    // 求差集
    public static void delCross(HashSet<String> all, HashSet<String> cross){
        for(String item : cross){
            if(all.contains(item)){
                all.remove(item);
            }
        }
    }
}
