package com.example.roskilde_daycare;


import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;

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

    public ArrayList<String> getAttributeArray() {
        ArrayList<String> array = new ArrayList<String>();
        array.add(this.getFirstName());
        array.add(this.getLastName());
        array.add(this.getTime().toString());
        array.add(this.getDateOfBirth().toString());
        array.add(this.getCPR());
        return array;
    }
}
