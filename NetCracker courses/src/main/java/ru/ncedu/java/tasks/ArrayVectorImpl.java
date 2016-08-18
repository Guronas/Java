package ru.ncedu.java.tasks;

import static java.lang.Math.sqrt;

import java.util.Arrays;

/**
 * Created by Frolov Maksim on 03.03.2016.
 */
public class ArrayVectorImpl implements ArrayVector {
    public ArrayVectorImpl() {
    }

    public ArrayVectorImpl(double... elems) {
        elements = elems;
    }

    private double[] elements;

    @Override
    public void set(double... elements) {
        this.elements = elements;
    }

    @Override
    public double[] get() {
        return elements;
    }

    @Override
    public ArrayVector clone() {
        return new ArrayVectorImpl(elements.clone());
    }

    @Override
    public int getSize() {
        return elements.length;
    }

    @Override
    public double getMax() {
        double max = elements[0];
        for (double element : elements) {
            if (element > max) {
                max = element;
            }
        }
        return max;
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        return elements[index];
    }

    @Override
    public void set(int index, double value) {
        if (index >= 0 & index < getSize()) {
            elements[index] = value;
        } else if (index >= getSize()) {
            set(Arrays.copyOf(get(), ++index));
            elements[getSize() - 1] = value;
        }
    }

    @Override
    public double getMin() {
        double min = elements[0];
        for (double element : elements) {
            if (element < min) {
                min = element;
            }
        }
        return min;
    }

    @Override
    public void sortAscending() {
        Arrays.sort(elements);
    }

    @Override
    public void mult(double factor) {
        for (int i = 0; i < getSize(); i++) {
            elements[i] *= factor;
        }
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        int length = (this.getSize() > anotherVector.getSize()) ? anotherVector.getSize() : this.getSize();
        for (int i = 0; i < length; i++) {
            elements[i] = this.get(i) + anotherVector.get(i);
        }
        return this;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double mult = 0;
        int length = (this.getSize() > anotherVector.getSize()) ? anotherVector.getSize() : this.getSize();
        for (int i = 0; i < length; i++) {
            mult += this.get(i) * anotherVector.get(i);
        }
        return mult;
    }

    @Override
    public double getNorm() {
        return sqrt(scalarMult(this));
    }
}
