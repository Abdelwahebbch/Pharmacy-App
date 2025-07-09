package com.pharmacy.Model;

import java.sql.Date;


public class Patient {
    private String id;
    private String name;
    private String phone;
    private Date birthday;
    private String note;


    public Patient(String id, String name, String phone, Date birthday, String note) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return this.name + " / " + this.phone;
    }

}
