package ru.ncedu.java.tasks;

import java.util.*;

/**
 * Created by Frolov Maksim on 06.05.2016.
 */

public class BusinessCardImpl implements BusinessCard {
    public BusinessCardImpl() {
    }

    private String employee;
    private String department;
    private int salary;
    private int age;
    private String gender;
    private String phoneNumber;


    @Override
    public BusinessCard getBusinessCard(Scanner scanner) throws NoSuchElementException {
        String input = scanner.next();
        if (!input.matches(".+;.+;.+;(0[1-9]|[1-2][0-9]|3[01])-(0[1-9]|1[012])-\\d\\d\\d\\d;[FM];\\d+;\\d{10}"))
            throw new InputMismatchException();
        String[] splitString = input.split(";");
        int sal = Integer.valueOf(splitString[5]);
        if (sal > 1000 && sal < 100) throw new InputMismatchException();

        employee = splitString[0] + " " + splitString[1];
        department = splitString[2];

        String[] date = splitString[3].split("-");
        Calendar today = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        birthday.set(Integer.valueOf(date[2]), Integer.valueOf(date[1]), Integer.valueOf(date[0]));
        if (today.get(Calendar.MONTH) - birthday.get(Calendar.MONTH) < 0) {
            age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR) - 1;
        } else {
            age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        }

        gender = splitString[4].equalsIgnoreCase("M") ? "Male" : "Female";
        salary = sal;

        phoneNumber = "+7 " + splitString[6].substring(0, 3) + "-" + splitString[6].substring(3, 6) + "-"
                + splitString[6].substring(6, 8) + "-" + splitString[6].substring(8);

        return this;
    }

    @Override
    public String getEmployee() {
        return employee;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
