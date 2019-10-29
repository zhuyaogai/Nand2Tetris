package com.fantasy.vm;

import com.fantasy.vm.cmd.*;
import com.fantasy.vm.codewriter.CodeWriter;
import com.fantasy.vm.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> files = new ArrayList<>();
        File path = new File(args[0]);
        if (path.isDirectory()) {
            File[] listFiles = path.listFiles();
            if (listFiles != null) {
                for (File file: listFiles) {
                    if (file.getName().endsWith(".vm")) {
                        files.add(file.getAbsolutePath());
                    }
                }
            }
        } else {
            files.add(args[0]);
        }

        if (files.size() == 0) {
            System.out.println("没有输入文件!请检查输入参数是否正确!");
            System.exit(-1);
        }


        CodeWriter codeWriter = new CodeWriter(args[1]);
        codeWriter.wirteInit();

        for (String file: files) {
            codeWriter.setFileName(file);
            Parser parser = new Parser(file);
            while (parser.hasMoreCommands()) {
                parser.advance();
                Class<?> clazz = parser.commandType();

                if (clazz.equals(C_ARITHMETIC.class)) {
                    codeWriter.writeArithmetic(((C_ARITHMETIC)parser.getCurrentCMD()));
                } else if (clazz.equals(C_POP.class) || clazz.equals(C_PUSH.class)){
                    codeWriter.writePushPop(parser.getCurrentCMD());
                } else if (clazz.equals(C_IF.class)) {
                    codeWriter.writeIf(((C_IF)parser.getCurrentCMD()).getLabel());
                } else if (clazz.equals(C_GOTO.class)) {
                    codeWriter.writeGoto(((C_GOTO)parser.getCurrentCMD()).getLabel());
                } else if (clazz.equals(C_LABEL.class)) {
                    codeWriter.writeLabel(((C_LABEL)parser.getCurrentCMD()).getLabel());
                } else if (clazz.equals(C_CALL.class)) {
                    C_CALL call = (C_CALL)parser.getCurrentCMD();
                    codeWriter.writeCall(call.getFunctionName(), call.getNumArgs());
                } else if (clazz.equals(C_FUNCTION.class)) {
                    C_FUNCTION function = (C_FUNCTION)parser.getCurrentCMD();
                    codeWriter.writeFunction(function.getFunctionName(), function.getNumLocals());
                } else {
                    codeWriter.writeReturn();
                }
            }
        }

        codeWriter.close();
    }
}
