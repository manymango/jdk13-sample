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
        map.put("111", "1");
        map.put("1111", "2");
        map.put("1111", "1");
        System.out.println("xixi");
    }
}
