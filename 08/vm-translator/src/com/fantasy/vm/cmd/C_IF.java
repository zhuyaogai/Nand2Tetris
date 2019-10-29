package com.fantasy.vm.cmd;

public class C_IF extends Command{
    private String label;

    public C_IF(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
