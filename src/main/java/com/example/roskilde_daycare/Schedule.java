package com.example.roskilde_daycare;


import java.sql.Time;

public class Schedule {

    private String group;
    private Time startTime;
    private Time endTime;
    private String day;
    private String employeeName;

    public Schedule(String group, Time startTime, Time endTime, String day, String employeeName){
        this.group = group;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.employeeName = employeeName;
    }

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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }





}
