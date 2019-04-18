package com.fantasy.cmd;

import com.fantasy.utils.StringUtil;

public class A_COMMAND extends Command{
    private String cmd;
    public A_COMMAND(String cmd) {
        this.cmd = StringUtil.getFirstPart(cmd);
        System.out.println(this.cmd);
    }

    // 返回十进制数字或者符号
    public Object symbol() {
        String sb = cmd.substring(1);
        if (Character.isDigit(sb.charAt(0))) {
            return Integer.parseInt(sb);
        } else {
            return sb;
        }
    }
}
