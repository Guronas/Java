package ru.ncedu.java.tasks;

import static java.lang.Math.sin;

/**
 * Created by Frolov Maksim on 25.02.2016.
 */

public class ControlFlowStatements1Impl implements ControlFlowStatements1 {

    public ControlFlowStatements1Impl() {
    }

    public float getFunctionValue(float x) {
        float f;
        if (x > 0) {
            f = 2 * (float) sin(x);
            return f;
        } else {
            f = 6 - x;
            return f;
        }
    }

    public String decodeWeekday(int weekday) {
        switch (weekday) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            default:
                return null;
        }
    }

    public int[][] initArray() {
        int array[][] = new int[8][5];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                array[i][j] = i * j;
            }
        }
        return array;
    }

    public int getMinValue(int[][] array) {
        int a = array[0][0];
        for (int[] x : array) {
            for (int y : x) {
                if (y <= a) {
                    a = y;
                }
            }
        }
        return a;
    }

    public BankDeposit calculateBankDeposit(double P) {
        double amount = 1000;
        double endAmount = 5000;
        int years = 0;
        while (amount < endAmount) {
            amount = amount + (amount * P / 100);
            System.out.println(amount);
            years++;
        }
        BankDeposit bankDeposit = new BankDeposit();
        bankDeposit.amount = amount;
        bankDeposit.years = years;
        return bankDeposit;
    }

    public static void main(String[] args) {
        ControlFlowStatements1Impl c = new ControlFlowStatements1Impl();
        int arr[][] = {{1}};
        int arr1[][] = {
                {10, 5, 3},
                {5, 8, 7},
                {7, 2, 3}
        };
        System.out.println(c.getMinValue(arr));
        System.out.println(c.getMinValue(arr1));
    }
}
