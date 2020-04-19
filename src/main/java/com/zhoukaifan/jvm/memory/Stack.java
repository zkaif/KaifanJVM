package com.zhoukaifan.jvm.memory;

import lombok.Getter;

import java.nio.ByteBuffer;

public class Stack extends java.util.Stack<Stack.Frame> {

    @Getter
    public static class Frame {
        private ByteBuffer localVar;
        private java.util.Stack<Object> operandStack;

        public Frame(int localVarSize, int operandStackSize) {
            this.localVar = ByteBuffer.wrap(new byte[localVarSize]);
            this.operandStack = new java.util.Stack<>();
            this.operandStack.setSize(operandStackSize);
        }
    }
}
