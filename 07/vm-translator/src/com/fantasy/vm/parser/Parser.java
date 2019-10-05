package com.fantasy.vm.parser;

import com.fantasy.vm.cmd.Command;
import com.fantasy.vm.lexer.Lexer;

import java.io.FileNotFoundException;

public class Parser {

    private Command currentCMD = null;
    private Command nextCMD = null;
    private Lexer lexer;

    public Parser(String fileName) throws FileNotFoundException {
        lexer = new Lexer(fileName);
    }

    public boolean hasMoreCommands() {
        nextCMD = lexer.nextCommand();
        return nextCMD != null;
    }

    public void advance() {
        currentCMD = nextCMD;
    }

    public Command getCurrentCMD() {
        return currentCMD;
    }

    // 返回命令的类型
    public Class commandType() {
        return currentCMD.getClass();
    }
}
