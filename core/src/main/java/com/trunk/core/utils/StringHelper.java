package com.trunk.core.utils;

import java.util.Random;

/**
 * Created by Administrator on 2019/7/23.
 */
public class StringHelper {

    public static Boolean IsNullOrEmpty(String value) {
        return (value == null || value.length() == 0);
    }

    public static String XmlEncode(String input) {

        if (IsNullOrEmpty(input) == true) {
            return "";
        }
        input = input.replace("&", "&amp;");
        input = input.replace("<", "&lt;");
        input = input.replace(">", "&gt;");
        return input;

    }

    static StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
    static Random random = new Random();

    public static String getRandomString(int length) {

        StringBuffer sb = new StringBuffer();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }


    static StringBuffer numberBuffer = new StringBuffer("0123456789");

    public static String getRandomNumber(int length) {

        StringBuffer sb = new StringBuffer();
        int range = numberBuffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(numberBuffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }
}
