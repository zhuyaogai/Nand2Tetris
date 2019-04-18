package com.fantasy;

import com.fantasy.cmd.A_COMMAND;
import com.fantasy.code.Code;
import com.fantasy.parser.Parser;
import com.fantasy.st.SymbolTable;
import com.fantasy.utils.StringUtil;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        String fileName = "src/Pong.asm";
//        String outputFileName = "src/Pong.hack";
        FileOutputStream fos = new FileOutputStream(args[1]);

        /*
         *  首先建立符号表，先初始化一些符号，然后再在原来的基础上做第一次扫描
         *  主要是解决 Label 的应用 (因为Label是可以先引用后声明的)
         */
        SymbolTable st = new SymbolTable();
        st.firstPass(args[0]);

        // st.printTable();
        Parser parser = new Parser(args[0]);
        Code code = new Code();

        while (parser.hasMoreCommands()) {
            parser.advance();
            String instruction;      // 将要生成的指令
            if (parser.commandType() == A_COMMAND.class) {
                Object symbol = parser.symbol();
                if (symbol instanceof Integer) {
                    instruction = StringUtil.decimalToBinary(((int)symbol));
                } else {
                    instruction = StringUtil.decimalToBinary(st.getAddress((String)symbol));
                }
            } else {
                String c = parser.comp();
                String d = parser.dest();
                String j = parser.jump();

                String cc = code.comp(c);
                String dd = code.dest(d);
                String jj = code.jump(j);

                instruction = "111" + cc + dd + jj;
            }

            instruction += "\n";
            fos.write(instruction.getBytes());
            fos.flush();

            System.out.print(instruction);
        }

        fos.close();

        st.printTable();   // 打印符号表
    }

}
