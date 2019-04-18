package com.fantasy.code;

import java.util.HashMap;
import java.util.Map;

public class Code {

    private Map<String, String> compA = new HashMap<>();
    private Map<String, String> compB = new HashMap<>();

    private Map<String, String> dest = new HashMap<>();
    private Map<String, String> jump = new HashMap<>();

    public Code() {
        init();
    }

    private void init() {
        // 填充compA when a=0
        compA.put("0", "101010");
        compA.put("1", "111111");
        compA.put("-1", "111010");
        compA.put("D", "001100");
        compA.put("A", "110000");
        compA.put("!D", "001101");
        compA.put("!A", "110001");
        compA.put("-D", "001111");
        compA.put("-A", "110011");
        compA.put("D+1", "011111");
        compA.put("A+1", "110111");
        compA.put("D-1", "001110");
        compA.put("A-1", "110010");
        compA.put("D+A", "000010");
        compA.put("D-A", "010011");
        compA.put("A-D", "000111");
        compA.put("D&A", "000000");
        compA.put("D|A", "010101");

        // 填充compB when a=1
        compB.put("M", "110000");
        compB.put("!M", "110001");
        compB.put("-M", "110011");
        compB.put("M+1", "110111");
        compB.put("M-1", "110010");
        compB.put("D+M", "000010");
        compB.put("D-M", "010011");
        compB.put("M-D", "000111");
        compB.put("D&M", "000000");
        compB.put("D|M", "010101");

        // dest 指令
        dest.put(null, "000");
        dest.put("M", "001");
        dest.put("D", "010");
        dest.put("MD", "011");
        dest.put("A", "100");
        dest.put("AM", "101");
        dest.put("AD", "110");
        dest.put("AMD", "111");

        // jump 指令
        jump.put(null, "000");
        jump.put("JGT", "001");
        jump.put("JEQ", "010");
        jump.put("JGE", "011");
        jump.put("JLT", "100");
        jump.put("JNE", "101");
        jump.put("JLE", "110");
        jump.put("JMP", "111");
    }

    public String dest(String d) {
        return dest.get(d);
    }

    public String comp(String c) {
        if (compA.keySet().contains(c)) {
            return "0"+compA.get(c);
        } else {
            return "1"+compB.get(c);
        }
    }

    public String jump(String j) {
        return jump.get(j);
    }
}
