package com.fantasy.utils;

public class StringUtil {

    public static String getFirstPart(String cmd) {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i < cmd.length();++i) {
            if (cmd.charAt(i) != ' ')
                sb.append(cmd.charAt(i));
            else
                break;
        }

        return sb.toString();
    }

    public static String decimalToBinary(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i=15;i>=0;--i) {
            sb.append((n>>>i & 1));
        }

        return sb.toString();
    }

    // for test !
    public static void main(String[] args) {
        System.out.println(decimalToBinary(8));
    }
}
