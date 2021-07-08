package com.hanzoy.container_identification.utils;

import java.util.UUID;

public class UuidUtil {

    public static String[] chars = new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2",
            "3", "4", "5", "6", "7", "8", "9"
    };

    public static String getShortUuid(){
        StringBuilder stringBuilder = new StringBuilder();
        String uuid = UuidUtil.getUuid();
        for(int i=0; i<8; i++){
            String str = uuid.substring(i*4, i*4+4);
            int x = Integer.parseInt(str, 16);
            stringBuilder.append(chars[x % 62]);
        }
        return stringBuilder.toString();
    }
    public static String getUuid(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
}
