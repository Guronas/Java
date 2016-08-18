package ru.ncedu.java.tasks;

import static java.lang.Math.*;

/**
 * Created by Frolov Maksim on 13.03.2016.
 */
public class ControlFlowStatements3Impl implements ControlFlowStatements3 {

    @Override
    public double getFunctionValue(double x) {
        double f;
        if (x <= 0) {
            f = -x;
            return f;
        } else if (0 < x & x < 2) {
            f = x * x;
            return f;
        } else {
            f = 4;
            return f;
        }
    }

    @Override
    public String decodeSeason(int monthNumber) {
        switch (monthNumber) {
            case 12:
            case 1:
            case 2:
                return "Winter";
            case 3:
            case 4:
            case 5:
                return "Spring";
            case 6:
            case 7:
            case 8:
                return "Summer";
            case 9:
            case 10:
            case 11:
                return "Autumn";
            default:
                return "Error";
        }
    }

    @Override
    public long[][] initArray() {
        long array[][] = new long[8][5];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) array[i][j] = (long) pow(abs(i - j), 5);
        }
        return array;
    }

    @Override
    public int getMaxProductIndex(long[][] array) {
        int indexOfRow = 0;
        long maxComposition = 0;
        for (int i = 0; i < array.length; i++) {
            long rowComposition = 1;
            for (long j : array[i]) rowComposition = rowComposition * abs(j);
            if (rowComposition > maxComposition) {
                maxComposition = rowComposition;
                indexOfRow = i;
            }
        }
        return indexOfRow;
    }

    @Override
    public float calculateLineSegment(float A, float B) {
        float sumOfB = B;
        while (sumOfB + B <= A) {
            sumOfB += B;
        }
        return A - sumOfB;
    }

    public static void main(String[] args) {
        ControlFlowStatements3 testObject = new ControlFlowStatements3Impl();
        System.out.println(testObject.getFunctionValue(1));
        System.out.println(testObject.decodeSeason(10));
        long[][] arr = {{0, 0, -0}, {10, 101, -10}, {1, 1, 1}, {-9, 99, 9}};
        System.out.println(testObject.getMaxProductIndex(arr));
        System.out.println(testObject.calculateLineSegment(10, 10));
        System.out.println(testObject.calculateLineSegment(10, 5));
        System.out.println(testObject.calculateLineSegment(10, 7));
        System.out.println(testObject.calculateLineSegment(11, 3));
        System.out.println(testObject.calculateLineSegment(15, 9));
    }
}
