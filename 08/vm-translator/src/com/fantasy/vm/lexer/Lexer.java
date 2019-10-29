package com.fantasy.vm.lexer;

import com.fantasy.vm.cmd.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Lexer {

    private Scanner sc;
    private List<String> arithmeticAndLogicalCommands;
    private List<String> memoryAccessCommands;

    public Lexer(String fileName) throws FileNotFoundException {
        sc = new Scanner(new FileInputStream(fileName));
        init();
    }

    private void init() {
        // 算术运算运算符
        arithmeticAndLogicalCommands = new ArrayList<>();
        arithmeticAndLogicalCommands.addAll(Arrays.asList("add", "sub", "neg", "eq", "gt", "lt", "and", "or", "not"));

        // 内存访问命令
        memoryAccessCommands = new ArrayList<>();
        memoryAccessCommands.addAll(Arrays.asList("push", "pop", "call", "ret", "label", "if-goto", "goto"));
    }

    public Command nextCommand() {
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine().trim();

            // 空行或者是注释的一行
            if (nextLine.length() == 0 || nextLine.charAt(0) == '/')
                continue;

            // nextLine 需要去除注释再比较
            if (nextLine.contains("//")) {
                nextLine = nextLine.substring(0, nextLine.indexOf('/')).trim();
            }

            if (arithmeticAndLogicalCommands.contains(nextLine)) {
                return new C_ARITHMETIC(nextLine);
            } else {
                // 接下来就是 内存访问命令  push pop call ret 等命令
                String[] strs = nextLine.split("\\s+");
                switch (strs[0]) {
                    case "push":
                        return new C_PUSH(strs[1], Integer.parseInt(strs[2]));
                    case "pop":
                        return new C_POP(strs[1], Integer.parseInt(strs[2]));
                    case "label":
                        return new C_LABEL(strs[1]);
                    case "goto":
                        return new C_GOTO(strs[1]);
                    case "if-goto":
                        return new C_IF(strs[1]);
                    case "call":
                        return new C_CALL(strs[1], Integer.parseInt(strs[2]));
                    case "function":
                        return new C_FUNCTION(strs[1], Integer.parseInt(strs[2]));
                    case "return":
                        return new C_RETURN();
                }
            }
        }

        sc.close();
        return null;
    }
}
