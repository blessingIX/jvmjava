package com.jvmjava.ch04.rtda;

public class Frame {

    public Frame lower;

    LocalVars localVars;

    OperandStack operandStack;

    public Frame(int maxLocals, int maxStack) {
        this.localVars = LocalVars.newLocalVars(maxLocals);
        this.operandStack = OperandStack.newOperandStack(maxStack);
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

}
