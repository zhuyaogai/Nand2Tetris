package com.fantasy.parser;

import com.fantasy.cmd.A_COMMAND;
import com.fantasy.cmd.C_COMMAND;
import com.fantasy.cmd.Command;
import com.fantasy.lexer.Lexer;

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

    public Object symbol() {
        if (currentCMD instanceof A_COMMAND) {
            return ((A_COMMAND)currentCMD).symbol();
        }

        return null;
    }

    public String dest() {
        if (currentCMD instanceof C_COMMAND) {
            return ((C_COMMAND)currentCMD).getDest();
        }

        return null;
    }

    public String comp() {
        if (currentCMD instanceof C_COMMAND) {
            return ((C_COMMAND)currentCMD).getComp();
        }

        return null;
    }

    public String jump() {
        if (currentCMD instanceof C_COMMAND) {
            return ((C_COMMAND)currentCMD).getJump();
        }

        return null;
    }

    public Class commandType() {
        if (currentCMD instanceof A_COMMAND)
            return A_COMMAND.class;
        else
            return C_COMMAND.class;
    }
}
