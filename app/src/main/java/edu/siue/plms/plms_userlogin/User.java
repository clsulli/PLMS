package edu.siue.plms.plms_userlogin;

/**
 * Created by Clint on 3/14/2018.
 */

import com.google.firebase.database.IgnoreExtraProperties;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class User
{

    public String name;
    public String eid;
    public String email;
    public String startStamp;
    public String stopStamp;
    public String lotID;
    public double lotPrice;
    public double totalDue;

    public User() {}

    public User(String email)
    {
        this.name = "Not Set";
        this.eid = "Not Set";
        this.email = email;
        this.startStamp = "Null";
        this.stopStamp = "Null";
        this.lotID = "Null";
        this.lotPrice = 0.0;
        this.totalDue = 0.0;
    }

    public User(String name, String eid, String email)
    {
        this.name = name;
        this.eid = eid;
        this.email = email;
        this.startStamp = "Null";
        this.stopStamp = "Null";
        this.lotID = "Null";
        this.lotPrice = 0.0;
        this.totalDue = 0.0;
    }
}
