package com.fantasy.vm.cmd;

public class C_FUNCTION extends Command {
    private String functionName;
    private int numLocals;

    public C_FUNCTION(String functionName, int numLocals) {
        this.functionName = functionName;
        this.numLocals = numLocals;
    }

    public int getNumLocals() {
        return numLocals;
    }

    public String getFunctionName() {
        return functionName;
    }
}
