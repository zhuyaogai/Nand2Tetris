package com.fantasy.vm.cmd;

public class C_GOTO extends Command {
    private String label;

    public C_GOTO(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
