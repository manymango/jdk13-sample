package com.manymango;

import java.util.HashMap;
import java.util.Map;

/**
 * @author manymango
 * @time 2019/11/9 10:54
 * @description
 */
public class MapDebug {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(1);
        for (int i=0; i<=100; ++i) {
            map.put(i+"", i+"");
        }
        System.out.println(map.values());
        System.out.println("xixi");
    }
}
