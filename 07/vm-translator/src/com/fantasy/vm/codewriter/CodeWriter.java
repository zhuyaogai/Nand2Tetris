package com.fantasy.vm.codewriter;

import com.fantasy.vm.cmd.C_ARITHMETIC;
import com.fantasy.vm.cmd.C_POP;
import com.fantasy.vm.cmd.C_PUSH;
import com.fantasy.vm.cmd.Command;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CodeWriter {

    private FileOutputStream fos;
    private int index = 0;        // label 标记索引
    private String fileName;

    public CodeWriter(String outputFileName) throws FileNotFoundException {
        fos = new FileOutputStream(outputFileName);
    }

    public void setFileName(String fileName) {
        String[] paths = fileName.split("\\.")[0].split("\\\\");
        this.fileName = paths[paths.length-1];
    }

    public void writeArithmetic(C_ARITHMETIC cmd) throws IOException {
        String c = cmd.arg1();       // 命令本身
        StringBuilder sb = new StringBuilder();

        switch (c) {
            case "add":
                /*
                    @SP
                    AM=M-1       // 栈也要向上回退1
                    D=M      // y
                    A=A-1
                    M=M+D
                 */
                sb.append("@SP\n");
                sb.append("AM=M-1\n");
                sb.append("D=M\n");
                sb.append("A=A-1\n");
                sb.append("M=M+D\n");
                break;
            case "sub":
                /*
                    @SP
                    AM=M-1       // 栈也要向上回退1
                    D=M
                    A=A-1
                    M=M-D
                 */
                sb.append("@SP\n");
                sb.append("AM=M-1\n");
                sb.append("D=M\n");
                sb.append("A=A-1\n");
                sb.append("M=M-D\n");
                break;
            case "neg":
                /*
                    @SP
                    A=M-1
                    M=-M
                 */
                sb.append("@SP\n");
                sb.append("A=M-1\n");
                sb.append("M=-M\n");
                break;
            case "eq":
                /*
                    @SP
                    AM=M-1
                    D=M
                    A=A-1
                    D=M-D
                    @TRUEx
                    D;JEQ
                    @SP
                    A=M-1
                    M=0
                    @CONTINUEx
                    0;JMP
                    (TRUEx)      // true 置 -1  false 置 0
                    @SP
                    A=M-1
                    M=-1
                    (CONTINUEx)
                 */
                sb.append("@SP\n");
                sb.append("AM=M-1\n");
                sb.append("D=M\n");
                sb.append("A=A-1\n");
                sb.append("D=M-D\n");
                sb.append("@TRUE").append(index).append("\n");
                sb.append("D;JEQ\n");
                sb.append("@SP\n");
                sb.append("A=M-1\n");
                sb.append("M=0\n");
                sb.append("@CONTINUE").append(index).append("\n");
                sb.append("0;JMP\n");
                sb.append("(TRUE").append(index).append(")\n");
                sb.append("@SP\n");
                sb.append("A=M-1\n");
                sb.append("M=-1\n");
                sb.append("(CONTINUE").append(index).append(")\n");
                ++index;
                break;
            case "gt":
                /*
                    @SP
                    AM=M-1
                    D=M
                    A=A-1
                    D=M-D             // x-y
                    @TRUEx
                    D;JGT
                    @SP
                    A=M-1
                    M=0
                    @CONTINUEx
                    0;JMP
                    (TRUEx)      // true 置 -1  false 置 0
                    @SP
                    A=M-1
                    M=-1
                    (CONTINUEx)
                 */
                sb.append("@SP\n");
                sb.append("AM=M-1\n");
                sb.append("D=M\n");
                sb.append("A=A-1\n");
                sb.append("D=M-D\n");
                sb.append("@TRUE").append(index).append("\n");
                sb.append("D;JGT\n");
                sb.append("@SP\n");
                sb.append("A=M-1\n");
                sb.append("M=0\n");
                sb.append("@CONTINUE").append(index).append("\n");
                sb.append("0;JMP\n");
                sb.append("(TRUE").append(index).append(")\n");
                sb.append("@SP\n");
                sb.append("A=M-1\n");
                sb.append("M=-1\n");
                sb.append("(CONTINUE").append(index).append(")\n");
                ++index;
                break;
            case "lt":
                /*
                    @SP
                    AM=M-1
                    D=M
                    A=A-1
                    D=M-D             // x-y
                    @TRUEx
                    D;JGT
                    @SP
                    A=M-1
                    M=0
                    @CONTINUEx
                    0;JMP
                    (TRUEx)      // true 置 -1  false 置 0  -1 的补码就是全 1
                    @SP
                    A=M-1
                    M=-1
                    (CONTINUEx)
                 */
                sb.append("@SP\n");
                sb.append("AM=M-1\n");
                sb.append("D=M\n");
                sb.append("A=A-1\n");
                sb.append("D=M-D\n");
                sb.append("@TRUE").append(index).append("\n");
                sb.append("D;JLT\n");
                sb.append("@SP\n");
                sb.append("A=M-1\n");
                sb.append("M=0\n");
                sb.append("@CONTINUE").append(index).append("\n");
                sb.append("0;JMP\n");
                sb.append("(TRUE").append(index).append(")\n");
                sb.append("@SP\n");
                sb.append("A=M-1\n");
                sb.append("M=-1\n");
                sb.append("(CONTINUE").append(index).append(")\n");
                ++index;
                break;
            case "and":
                /*
                    @SP
                    AM=M-1
                    D=M
                    A=A-1
                    M=D&M
                 */
                sb.append("@SP\n");
                sb.append("AM=M-1\n");
                sb.append("D=M\n");
                sb.append("A=A-1\n");
                sb.append("M=D&M\n");
                break;
            case "or":
                /*
                    @SP
                    AM=M-1
                    D=M
                    A=A-1
                    M=D|M
                 */
                sb.append("@SP\n");
                sb.append("AM=M-1\n");
                sb.append("D=M\n");
                sb.append("A=A-1\n");
                sb.append("M=D|M\n");
                break;
            case "not":
                /*
                    @SP
                    A=M-1
                    M=!M
                 */
                sb.append("@SP\n");
                sb.append("A=M-1\n");
                sb.append("M=!M\n");
                break;
        }

        fos.write(sb.toString().getBytes());
    }

    /**
     *      只有在 C instruction 的时候才会有可能跳转,其他的时候都是 Pc(t) = Pc(t-1) + 1
     * @param cmd
     * @throws IOException
     */
    public void writePushPop(Command cmd) throws IOException {
        StringBuilder sb = new StringBuilder();

        if (cmd instanceof C_PUSH) {
            C_PUSH push = (C_PUSH)cmd;
            String segment = push.arg1();
            int position = push.arg2();

            switch (segment) {
                case "constant":
                    /*
                        @position
                        D=A
                        @SP
                        A=M
                        M=D
                        @SP
                        M=M+1
                     */
                    sb.append("@").append(position).append("\n");
                    sb.append("D=A\n");
                    sb.append("@SP\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("M=M+1\n");
                    break;
                case "local":
                    /*
                        @LCL
                        D=M
                        @position
                        A=D+A
                        D=M
                        @SP
                        A=M
                        M=D
                        @SP
                        M=M+1
                     */
                    sb.append("@LCL\n");
                    sb.append("D=M\n");
                    sb.append("@").append(position).append("\n");
                    sb.append("A=D+A\n");
                    sb.append("D=M\n");
                    sb.append("@SP\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("M=M+1\n");
                    break;
                case "argument":
                    /*
                        @ARG
                        D=M
                        @position
                        A=D+A
                        D=M
                        @SP
                        A=M
                        M=D
                        @SP
                        M=M+1
                     */
                    sb.append("@ARG\n");
                    sb.append("D=M\n");
                    sb.append("@").append(position).append("\n");
                    sb.append("A=D+A\n");
                    sb.append("D=M\n");
                    sb.append("@SP\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("M=M+1\n");
                    break;
                case "this":
                    /*
                        @THIS
                        D=M
                        @position
                        A=D+A
                        D=M
                        @SP
                        A=M
                        M=D
                        @SP
                        M=M+1
                     */
                    sb.append("@THIS\n");
                    sb.append("D=M\n");
                    sb.append("@").append(position).append("\n");
                    sb.append("A=D+A\n");
                    sb.append("D=M\n");
                    sb.append("@SP\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("M=M+1\n");
                    break;
                case "that":
                    /*
                        @THAT
                        D=M
                        @position
                        A=D+A
                        D=M
                        @SP
                        A=M
                        M=D
                        @SP
                        M=M+1
                     */
                    sb.append("@THAT\n");
                    sb.append("D=M\n");
                    sb.append("@").append(position).append("\n");
                    sb.append("A=D+A\n");
                    sb.append("D=M\n");
                    sb.append("@SP\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("M=M+1\n");
                    break;
                case "temp":
                    /*
                        // push temp position
                        @R5
                        D=A
                        @position
                        A=A+D
                        D=M
                        @SP
                        A=M
                        M=D
                        @SP
                        M=M+1
                     */
                    sb.append("@R5\n");
                    sb.append("D=A\n");
                    sb.append("@").append(position).append("\n");
                    sb.append("A=A+D\n");
                    sb.append("D=M\n");
                    sb.append("@SP\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("M=M+1\n");
                    break;
                case "pointer":
                    /*
                        @p
                        D=M
                        @SP
                        A=M
                        M=D
                        @SP
                        M=M+1
                     */
                    sb.append("@").append(position==0?"THIS":"THAT").append("\n");
                    sb.append("D=M\n");
                    sb.append("@SP\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("M=M+1\n");
                    break;
                case "static":
                    /*
                        // push static position
                        // 这个segment有点特殊,它是利用Hack语言规范来进行实现的,这也是为了保证static变量的全局唯一性

                        @filename.position
                        D=M
                        @SP
                        A=M
                        M=D
                        @SP
                        M=M+1
                     */
                    sb.append("@").append(fileName).append(".").append(position).append("\n");
                    sb.append("D=M\n");
                    sb.append("@SP\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("M=M+1\n");
                    break;
            }
        } else if (cmd instanceof C_POP) {
            C_POP pop = (C_POP) cmd;
            String segment = pop.arg1();
            int position = pop.arg2();

            switch (segment) {
                case "local":
                    /*
                        // pop local position

                        @LCL
                        D=M
                        @position
                        D=D+A
                        @R13
                        M=D
                        @SP
                        AM=M-1
                        D=M
                        @R13
                        A=M
                        M=D
                     */
                    sb.append("@LCL\n");
                    sb.append("D=M\n");
                    sb.append("@").append(position).append("\n");
                    sb.append("D=D+A\n");
                    sb.append("@R13\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("AM=M-1\n");
                    sb.append("D=M\n");
                    sb.append("@R13\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    break;
                case "argument":
                    /*
                        @ARG
                        D=M
                        @position
                        D=D+A
                        @R13
                        M=D
                        @SP
                        AM=M-1
                        D=M
                        @R13
                        A=M
                        M=D
                     */
                    sb.append("@ARG\n");
                    sb.append("D=M\n");
                    sb.append("@").append(position).append("\n");
                    sb.append("D=D+A\n");
                    sb.append("@R13\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("AM=M-1\n");
                    sb.append("D=M\n");
                    sb.append("@R13\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    break;
                case "this":
                    /*
                        @THIS
                        D=M
                        @position
                        D=D+A
                        @R13
                        M=D
                        @SP
                        AM=M-1
                        D=M
                        @R13
                        A=M
                        M=D
                     */
                    sb.append("@THIS\n");
                    sb.append("D=M\n");
                    sb.append("@").append(position).append("\n");
                    sb.append("D=D+A\n");
                    sb.append("@R13\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("AM=M-1\n");
                    sb.append("D=M\n");
                    sb.append("@R13\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    break;
                case "that":
                    /*
                        @THAT
                        D=M
                        @position
                        D=D+A
                        @R13
                        M=D
                        @SP
                        AM=M-1
                        D=M
                        @R13
                        A=M
                        M=D
                     */
                    sb.append("@THAT\n");
                    sb.append("D=M\n");
                    sb.append("@").append(position).append("\n");
                    sb.append("D=D+A\n");
                    sb.append("@R13\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("AM=M-1\n");
                    sb.append("D=M\n");
                    sb.append("@R13\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    break;
                case "temp":
                    /*
                        // pop temp position

                        @R5
                        D=A
                        @position
                        D=D+A
                        @R13
                        M=D
                        @SP
                        AM=M-1
                        D=M
                        @R13
                        A=M
                        M=D
                     */
                    sb.append("@R5\n");
                    sb.append("D=A\n");
                    sb.append("@").append(position).append("\n");
                    sb.append("D=D+A\n");
                    sb.append("@R13\n");
                    sb.append("M=D\n");
                    sb.append("@SP\n");
                    sb.append("AM=M-1\n");
                    sb.append("D=M\n");
                    sb.append("@R13\n");
                    sb.append("A=M\n");
                    sb.append("M=D\n");
                    break;
                case "pointer":
                    /*
                        // pop pointer position
                        @SP
                        AM=M-1
                        D=M
                        @p
                        M=D
                     */
                    sb.append("@SP\n");
                    sb.append("AM=M-1\n");
                    sb.append("D=M\n");
                    sb.append("@").append(position==0?"THIS":"THAT").append("\n");
                    sb.append("M=D\n");
                    break;
                case "static":
                    /*
                        // pop static position
                        @SP
                        AM=M-1
                        D=M
                        @filename.position
                        M=D
                     */
                    sb.append("@SP\n");
                    sb.append("AM=M-1\n");
                    sb.append("D=M\n");
                    sb.append("@").append(fileName).append(".").append(position).append("\n");
                    sb.append("M=D\n");
                    break;
            }
        }

        fos.write(sb.toString().getBytes());
    }

    public void close() throws IOException {
        fos.close();
    }
}
