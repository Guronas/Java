package com.netcracker.edu.java.tasks;

import java.util.Arrays;

/**
 * Created by Frolov Maksim on 18.03.2016.
 */
public class ComplexNumberImpl implements ComplexNumber {
    private double re;
    private double im;
    private String value;

    public ComplexNumberImpl() {
        re = 0;
        im = 0;
    }

    public ComplexNumberImpl(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public ComplexNumberImpl(String value) {
        this.value = value;
    }

    @Override
    public double getRe() {
        return this.re;
    }

    @Override
    public double getIm() {
        return this.im;
    }

    @Override
    public boolean isReal() {
        return im == 0;
    }

    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {

    }

    @Override
    public ComplexNumber copy() {
        return new ComplexNumberImpl(this.re, this.im);
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return copy();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ComplexNumber && compareTo((ComplexNumber) obj) == 0;
    }

    @Override
    public String toString() {
        if (im == 0) {
            return (((Double) re).toString());
        } else if (re == 0) {
            return (((Double) im).toString()) + "i";
        } else if (im > 0) {
            return (((Double) re).toString()) + "+" + (((Double) im).toString()) + "i";
        } else {
            return (((Double) re).toString()) + (((Double) im).toString()) + "i";
        }
    }

    @Override
    public int compareTo(ComplexNumber other) {
        double sumOfSquareThis = this.getRe() * this.getRe() + this.getIm() * this.getIm();
        double sumOfSquareOther = other.getRe() * other.getRe() + other.getIm() * other.getIm();
        if (sumOfSquareThis < sumOfSquareOther) {
            return -1;
        } else if (sumOfSquareThis == sumOfSquareOther) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array);
    }

    @Override
    public ComplexNumber negate() {
        this.set(-this.re, -this.im);
        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        this.set(this.re + arg2.getRe(), this.im + arg2.getIm());
        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        this.set((this.re * arg2.getRe()) - (this.im * arg2.getIm()),
                (this.im * arg2.getRe()) + (this.re * arg2.getIm()));
        return this;
    }
}
