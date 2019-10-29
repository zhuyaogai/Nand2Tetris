package com.fantasy.vm.cmd;

/**
 *   算术运算
 */
public class C_ARITHMETIC extends Command implements Arg1{
    private String cmd;       // 命令本身

    public C_ARITHMETIC(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String arg1() {
        return cmd;
    }
}
