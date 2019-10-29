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
    private int funcIndex = 0;      // 函数调用Index
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

    /*
       //  label xxx
            (xxx)
     */
    public void writeLabel(String label) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(label).append(")\n");

        fos.write(sb.toString().getBytes());
    }

    /*
        // goto label
            @label
            0;JMP

     */
    public void writeGoto(String label) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("@").append(label).append("\n");
        sb.append("0;JMP\n");

        fos.write(sb.toString().getBytes());
    }

    /*
       //  if-goto label
           @SP
           AM=M-1
           D=M
           @label
           D;JNE
     */
    public void writeIf(String label) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("@SP\n");
        sb.append("AM=M-1\n");
        sb.append("D=M\n");
        sb.append("@").append(label).append("\n");
        sb.append("D;JNE\n");

        fos.write(sb.toString().getBytes());
    }

    /*
            // call f n
            @f.return-addressx
            D=A
            @SP
            A=M
            M=D
            @SP
            M=M+1

            @LCL
            D=M
            @SP
            A=M
            M=D
            @SP
            M=M+1

            @ARG
            D=M
            @SP
            A=M
            M=D
            @SP
            M=M+1

            @THIS
            D=M
            @SP
            A=M
            M=D
            @SP
            M=M+1

            @THAT
            D=M
            @SP
            A=M
            M=D
            @SP
            M=M+1

            @n
            D=A
            @5
            D=D+A
            @SP
            D=M-D
            @ARG
            M=D
            @SP
            D=M
            @LCL
            M=D

            @f
            0;JMP
            (f.return-addressx)
     */
    public void writeCall(String functionName, int numArgs) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("@").append(functionName).append(".return-address").append(funcIndex).append("\n");
        sb.append("D=A\n");
        sb.append("@SP\n");
        sb.append("A=M\n");
        sb.append("M=D\n");
        sb.append("@SP\n");
        sb.append("M=M+1\n");

        sb.append("@LCL\n");
        sb.append("D=M\n");
        sb.append("@SP\n");
        sb.append("A=M\n");
        sb.append("M=D\n");
        sb.append("@SP\n");
        sb.append("M=M+1\n");

        sb.append("@ARG\n");
        sb.append("D=M\n");
        sb.append("@SP\n");
        sb.append("A=M\n");
        sb.append("M=D\n");
        sb.append("@SP\n");
        sb.append("M=M+1\n");

        sb.append("@THIS\n");
        sb.append("D=M\n");
        sb.append("@SP\n");
        sb.append("A=M\n");
        sb.append("M=D\n");
        sb.append("@SP\n");
        sb.append("M=M+1\n");

        sb.append("@THAT\n");
        sb.append("D=M\n");
        sb.append("@SP\n");
        sb.append("A=M\n");
        sb.append("M=D\n");
        sb.append("@SP\n");
        sb.append("M=M+1\n");

        /*
            @n
            D=A
            @5
            D=D+A
            @SP
            D=M-D
            @ARG
            M=D
            @SP
            D=M
            @LCL
            M=D

            @f
            0;JMP
            (f.return-addressx)
         */
        sb.append("@").append(numArgs).append("\n");
        sb.append("D=A\n");
        sb.append("@5\n");
        sb.append("D=D+A\n");
        sb.append("@SP\n");
        sb.append("D=M-D\n");
        sb.append("@ARG\n");
        sb.append("M=D\n");
        sb.append("@SP\n");
        sb.append("D=M\n");
        sb.append("@LCL\n");
        sb.append("M=D\n");
        sb.append("@").append(functionName).append("\n");
        sb.append("0;JMP\n");
        sb.append("(").append(functionName).append(".return-address").append(funcIndex).append(")\n");
        ++funcIndex;

        fos.write(sb.toString().getBytes());
    }

    /*
        // function f k
        (f)
        repeat k times:
            push 0
     */

    public void writeFunction(String functionName, int numLocals) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(functionName).append(")\n");
        while (numLocals > 0) {
            sb.append("@SP\n");
            sb.append("A=M\n");
            sb.append("M=0\n");
            sb.append("@SP\n");
            sb.append("M=M+1\n");
            --numLocals;
        }

        fos.write(sb.toString().getBytes());
    }


    /*
        // return

            @LCL
            D=M
            @R13
            M=D
            @5
            A=D-A
            D=M
            @R14
            M=D
            @SP
            A=M-1
            D=M
            @ARG
            A=M
            M=D
            @ARG
            D=M+1
            @SP
            M=D


            @R13
            DM=M-1
            A=D
            D=M
            @THAT
            M=D

            @R13
            DM=M-1
            A=D
            D=M
            @THIS
            M=D

            @R13
            DM=M-1
            A=D
            D=M
            @ARG
            M=D

            @R13
            DM=M-1
            A=D
            D=M
            @LCL
            M=D

            @R14
            A=M
            0;JMP        // A 只是指取内存的地址，不是说PC寄存器的值
     */

    public void writeReturn() throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append("@LCL\n");
        sb.append("D=M\n");
        sb.append("@R13\n");
        sb.append("M=D\n");
        sb.append("@5\n");
        sb.append("A=D-A\n");
        sb.append("D=M\n");
        sb.append("@R14\n");
        sb.append("M=D\n");
        sb.append("@SP\n");
        sb.append("A=M-1\n");
        sb.append("D=M\n");
        sb.append("@ARG\n");
        sb.append("A=M\n");
        sb.append("M=D\n");
        sb.append("@ARG\n");
        sb.append("D=M+1\n");
        sb.append("@SP\n");
        sb.append("M=D\n");


        sb.append("@R13\n");
        sb.append("MD=M-1\n");
        sb.append("A=D\n");
        sb.append("D=M\n");
        sb.append("@THAT\n");
        sb.append("M=D\n");

        sb.append("@R13\n");
        sb.append("MD=M-1\n");
        sb.append("A=D\n");
        sb.append("D=M\n");
        sb.append("@THIS\n");
        sb.append("M=D\n");

        sb.append("@R13\n");
        sb.append("MD=M-1\n");
        sb.append("A=D\n");
        sb.append("D=M\n");
        sb.append("@ARG\n");
        sb.append("M=D\n");

        sb.append("@R13\n");
        sb.append("MD=M-1\n");
        sb.append("A=D\n");
        sb.append("D=M\n");
        sb.append("@LCL\n");
        sb.append("M=D\n");

        sb.append("@R14\n");
        sb.append("A=M\n");
        sb.append("0;JMP\n");

        fos.write(sb.toString().getBytes());
    }

    /*
        @256
        D=A
        @SP
        M=D
        // call Sys.init 0
     */

    public void wirteInit() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("@256\n");
        sb.append("D=A\n");
        sb.append("@SP\n");
        sb.append("M=D\n");
        fos.write(sb.toString().getBytes());

        writeCall("Sys.init", 0);
    }

    public void close() throws IOException {
        fos.close();
    }
}
