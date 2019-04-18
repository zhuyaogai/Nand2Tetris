package com.fantasy.lexer;

import com.fantasy.cmd.A_COMMAND;
import com.fantasy.cmd.C_COMMAND;
import com.fantasy.cmd.Command;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lexer {
    private Scanner sc;
    private String nextLine;
    public Lexer(String fileName) throws FileNotFoundException {
        sc = new Scanner(new FileInputStream(fileName));
    }

    public Command nextCommand() {
        while (sc.hasNextLine()) {
            nextLine = sc.nextLine().trim();

            // 空行或者是注释的一行,还有 Label 行
            if (nextLine.length()==0 || nextLine.charAt(0) == '/' || nextLine.charAt(0) == '(')
                continue;

            // A_COMMAND
            if (nextLine.charAt(0) == '@') {
                return new A_COMMAND(nextLine);
            } else {   // C_COMMAND
                return new C_COMMAND(nextLine);
            }
        }

        sc.close();
        return null;
    }
}
