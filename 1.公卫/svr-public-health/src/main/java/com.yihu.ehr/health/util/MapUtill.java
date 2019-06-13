package com.yihu.ehr.health.util;

import java.util.Map;

/**
 * Created by zhangdan on 2018/4/17.
 */
public class MapUtill {

    /**
     * map获取字符串
     * @param map
     * @param key
     * @return
     */
    public static String getMapString(Map<String,Object> map, String key){
        Object o = map.get(key);
        if(o==null){
            return null;
        }
        return  o.toString();
    }

    /**
     * map获取int
     * @param map
     * @param key
     * @return
     */
    public static Integer getMapInt(Map<String,Object> map,String key){
        Object o = map.get(key);
        if(o==null){
            return null;
        }
        return Integer.parseInt(o.toString());
    }

    /**
     * map获取long
     * @param map
     * @param key
     * @return
     */
    public static Long getMapLong(Map<String,Object> map,String key){
        Object o = map.get(key);
        if(o==null){
            return null;
        }
        return Long.parseLong(o.toString());
    }
}
