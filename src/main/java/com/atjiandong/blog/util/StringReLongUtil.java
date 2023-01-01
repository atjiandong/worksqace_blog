package com.atjiandong.blog.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author atjiandong
 * @create 2022-09-15 15:16
 */
public class StringReLongUtil {

    public static List<Long> StringReLong(String str){
        List<Long> list = new ArrayList<>();
        if (!("".equals(str)) && str != null){
            String[] idarray = str.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
