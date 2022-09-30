package com.jvmjava.ch04.rtda;

public class LocalVars {

    Slot[] slots;

    private LocalVars(Slot[] slots) {
        this.slots = slots;
    }

    public static LocalVars newLocalVars(int maxLocals) {
        if (maxLocals > 0) {
            Slot[] slots = new Slot[maxLocals];
            for (int i = 0; i < slots.length; i++) {
                slots[i] = new Slot();
            }
            return new LocalVars(slots);
        }
        return null;
    }

    public void setInd(int index, int val) {
        this.slots[index].num = val;
    }

    public int getInt(int index) {
        return this.slots[index].num;
    }

    public void setFloat(int index, float val) {
        this.slots[index].num = (int) val;
    }

    public float getFloat(int index) {
        return (float) this.slots[index].num;
    }

    public void setLong(int index, long val) {
        this.slots[index].num = (int) val;
        this.slots[index + 1].num = (int) (val >> 32);
    }

    public long getLong(int index) {
        int low = this.slots[index].num;
        int high = this.slots[index].num;
        return ((long) high) << 32 | ((long) low);
    }

    public void setDouble(int index, double val) {
        this.setLong(index, (long) val);
    }

    public double getDouble(int index) {
        return (double) this.getLong(index);
    }

    public void setRef(int index, Object ref) {
        this.slots[index].ref = ref;
    }

    public Object getRef(int index) {
        return this.slots[index].ref;
    }

}
