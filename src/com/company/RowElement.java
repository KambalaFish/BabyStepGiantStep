package com.company;

public class RowElement implements Comparable<RowElement> {
    private final int value, index;
    private final RowType rowType;
    public RowElement(int value, int index, RowType rowType){
        this.value = value;
        this.index = index;
        this.rowType = rowType;
    }

    public int getValue() {
        return value;
    }

    public int getIndex(){
        return index;
    }

    public RowType getRowType(){
        return rowType;
    }

    @Override
    public int compareTo(RowElement o) {
        return Integer.compare(this.getValue(), o.getValue());
    }
}
