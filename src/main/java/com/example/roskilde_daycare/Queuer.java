package com.example.roskilde_daycare;


import java.sql.Timestamp;
import java.sql.Date;

public class Queuer extends Child{

    private Timestamp time;

    public Queuer(String firstName, String lastName, Date dateOfBirth, String gender, Parent parent, Timestamp time, String CPR){
        super(firstName, lastName, dateOfBirth, gender, parent, CPR);
        this.time = time;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
