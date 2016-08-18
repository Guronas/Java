package ru.ncedu.java.tasks;

/**
 * Created by Frolov Maksim on 03.03.2016.
 */

public class EmployeeImpl implements Employee {
    public EmployeeImpl() {
    }

    private int salary = 1000;
    private String firstName;
    private String lastName;
    private Employee manager;


    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void increaseSalary(int value) {
        salary += value;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String getManagerName() {
        return manager != null ? manager.getFullName() : "No manager";
    }

    @Override
    public Employee getTopManager() {
        if (this.manager != null) {
            return manager.getTopManager();
        } else {
            return this;
        }
    }

    public static void main(String... qwe) {
        Employee employee = new EmployeeImpl();
        employee.increaseSalary(12);
        System.out.println(employee.getSalary());
    }
}
