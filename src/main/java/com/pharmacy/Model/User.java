package com.pharmacy.Model;

public class User {
    private int cin;
    private String name;
    private String lastName;
    private int password;
    private String email;
    private String phone;

    private double salary;

    public User(int cin, String name, String lastName, String email, String phone, String password, double salary) {
        this.cin = cin;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password.hashCode();
        this.phone = phone;
        this.salary = salary;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
