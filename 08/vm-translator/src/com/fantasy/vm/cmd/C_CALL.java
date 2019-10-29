package com.fantasy.vm.cmd;

public class C_CALL extends Command {
    private String functionName;
    private int numArgs;

    public C_CALL(String functionName, int numArgs) {
        this.functionName = functionName;
        this.numArgs = numArgs;
    }

    public String getFunctionName() {
        return functionName;
    }

    public int getNumArgs() {
        return numArgs;
    }
}
