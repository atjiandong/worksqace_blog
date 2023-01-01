package com.atjiandong.blog.util;

/**
 * @author atjiandong
 * @create 2022-10-06 20:12
 */
public class StringTransformIntegerUtil {

    public static Integer caseInteger(String str){
        String trim = str.trim();
        int i = 0;
        try {
            i = Integer.parseInt(trim);
        } catch (NumberFormatException e) {
            System.out.println("进行数据转换");
        }
        return i;
    }


    public static Long caseLong(String str){
        String trim = str.trim();
        Long i = new Long(0);
        try {
            i = Long.parseLong(trim);
        } catch (NumberFormatException e) {
            System.out.println("进行数据转换");
        }
        return i;
    }
}
