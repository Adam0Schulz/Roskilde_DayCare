package com.example.roskilde_daycare;


import java.sql.Time;

public class Schedule {

    private String group;
    private String shift;
    private String day;
    private String employeeName;

    public Schedule(String group, String shift , String day, String employeeName){
        this.group = group;
        this.shift = shift;
        this.day = day;
        this.employeeName = employeeName;
    }

    public Schedule(){}

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getShift() { return shift; }

    public void setShift(String shift) { this.shift = shift; }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }





}
