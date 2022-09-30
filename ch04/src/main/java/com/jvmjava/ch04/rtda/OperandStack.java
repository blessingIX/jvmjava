package com.jvmjava.ch04.rtda;

public class OperandStack {

    int size;

    Slot[] slots;

    public OperandStack(Slot[] slots) {
        this.slots = slots;
    }

    public static OperandStack newOperandStack(int maxStack) {
        if (maxStack > 0) {
            Slot[] slots = new Slot[maxStack];
            for (int i = 0; i < slots.length; i++) {
                slots[i] = new Slot();
            }
            return new OperandStack(slots);
        }
        return null;
    }

    public void pushInt(int val) {
        this.slots[this.size].num = val;
        this.size++;
    }

    public int popInt() {
        this.size--;
        return this.slots[this.size].num;
    }

    public void pushFloat(float val) {
        this.slots[this.size].num = (int) val;
        this.size++;
    }

    public float popFloat() {
        this.size--;
        return (float) this.slots[this.size].num;
    }

    public void pushLong(long val) {
        this.slots[this.size].num = (int) val;
        this.slots[this.size + 1].num = (int) (val >> 32);
        this.size += 2;
    }

    public long popLong() {
        this.size -= 2;
        int low = (int) this.slots[this.size].num;
        int high = (int) this.slots[this.size + 1].num;
        return ((long) high) | (long) low;
    }

    public void pushDouble(double val) {
        this.pushLong((long) val);
    }

    public double popDouble() {
        return (double) this.popLong();
    }

    public void pushRef(Object ref) {
        this.slots[this.size].ref = ref;
        this.size++;
    }

    public Object popRef() {
        this.size--;
        Object ref = this.slots[this.size].ref;
        this.slots[this.size].ref = null;
        return ref;
    }

}
