package com.fantasy.vm;

import com.fantasy.vm.cmd.C_ARITHMETIC;
import com.fantasy.vm.codewriter.CodeWriter;
import com.fantasy.vm.parser.Parser;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser(args[0]);
        CodeWriter codeWriter = new CodeWriter(args[1]);
        codeWriter.setFileName(args[1]);

        while (parser.hasMoreCommands()) {
            parser.advance();

            if (parser.commandType().equals(C_ARITHMETIC.class)) {
                codeWriter.writeArithmetic(((C_ARITHMETIC)parser.getCurrentCMD()));
            } else {
                codeWriter.writePushPop(parser.getCurrentCMD());
            }
        }

        codeWriter.close();
    }
}
