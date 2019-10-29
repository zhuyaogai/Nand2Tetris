package com.fantasy.vm.cmd;

public class C_LABEL extends Command {
    private String label;

    public C_LABEL(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
