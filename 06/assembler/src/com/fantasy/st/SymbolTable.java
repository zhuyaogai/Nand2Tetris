package com.fantasy.st;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SymbolTable {
    private Map<String, Integer> table;
    private Integer counter = 0;
    private Integer addr = 16;

    public SymbolTable() {
        this.table = new HashMap<>();
        init();
    }

    private void init() {
        table.put("SP", 0);
        table.put("LCL", 1);
        table.put("ARG", 2);
        table.put("THIS", 3);
        table.put("THAT", 4);
        table.put("R0", 0);
        table.put("R1", 1);
        table.put("R2", 2);
        table.put("R3", 3);
        table.put("R4", 4);
        table.put("R5", 5);
        table.put("R6", 6);
        table.put("R7", 7);
        table.put("R8", 8);
        table.put("R9", 9);
        table.put("R10", 10);
        table.put("R11", 11);
        table.put("R12", 12);
        table.put("R13", 13);
        table.put("R14", 14);
        table.put("R15", 15);
        table.put("SCREEN", 16384);
        table.put("KBD", 24576);
    }

    private void addEntry(String symbol, int address) {
        table.put(symbol, address);
    }

    private boolean contains(String symbol) {
        return table.containsKey(symbol);
    }

    public int getAddress(String symbol) {
        // return table.get(symbol);

        if (!contains(symbol))
            addEntry(symbol, addr++);

        return table.get(symbol);
    }

    public void firstPass(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream(fileName));
        String nextLine;

        while (sc.hasNextLine()) {
            nextLine = sc.nextLine().trim();

            // 空行或者是注释的一行
            if (nextLine.length()==0 || nextLine.charAt(0) == '/')
                continue;

            if (nextLine.charAt(0) == '(') {
                String symbol = nextLine.substring(1, nextLine.length()-1);
                table.put(symbol, counter);
            } else {
                ++counter;
            }
        }

        sc.close();
    }

    public void printTable() {
        System.out.println(table);
    }
}
