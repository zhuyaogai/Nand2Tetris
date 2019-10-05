package com.fantasy.vm.cmd;

public class C_PUSH extends Command implements Arg1, Arg2{
    private String segment;
    private int position;

    public C_PUSH(String segment, int position) {
        this.segment = segment;
        this.position = position;
    }

    @Override
    public String arg1() {
        return segment;
    }

    @Override
    public int arg2() {
        return position;
    }
}
