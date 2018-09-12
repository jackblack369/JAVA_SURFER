package com.will.book.algorithm.chapter05;

/**
 * Created by 2YSP on 2017/12/24.
 */
public class Employee {
    private String name;
    private double salary;
    private int seniority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    @Override
    public boolean equals(Object o) {

        return o instanceof  Employee && name.equals(((Employee)o).name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
