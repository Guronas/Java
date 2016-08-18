package com.netcracker.edu.java.tasks;

/**
 * Created by Frolov Maksim.
 */
public class ZeroTaskImpl implements ZeroTask {

    private int integerValue = 0;

    public ZeroTaskImpl(){}

    @Override
    public void setIntegerValue(int value) {
        integerValue = value;
    }

    @Override
    public double getDoubleValue() {
        return integerValue;
    }


}
