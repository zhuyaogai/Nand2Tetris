package com.fantasy.vm.lexer;

import com.fantasy.vm.cmd.C_ARITHMETIC;
import com.fantasy.vm.cmd.C_POP;
import com.fantasy.vm.cmd.C_PUSH;
import com.fantasy.vm.cmd.Command;
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
        memoryAccessCommands.addAll(Arrays.asList("push", "pop", "call", "ret"));
    }

    public Command nextCommand() {
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine().trim();

            // 空行或者是注释的一行
            if (nextLine.length() == 0 || nextLine.charAt(0) == '/')
                continue;

            if (arithmeticAndLogicalCommands.contains(nextLine)) {
                return new C_ARITHMETIC(nextLine);
            } else {
                // 接下来就是 内存访问命令  push pop call ret 等命令
                String[] strs = nextLine.split(" ");
                switch (strs[0]) {
                    case "push":
                        return new C_PUSH(strs[1], Integer.parseInt(strs[2]));
                    case "pop":
                        return new C_POP(strs[1], Integer.parseInt(strs[2]));
                }
            }
        }

        sc.close();
        return null;
    }
}
