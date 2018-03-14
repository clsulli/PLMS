package edu.siue.plms.plms_userlogin;

/**
 * Created by Clint on 3/14/2018.
 */

import com.google.firebase.database.IgnoreExtraProperties;

public class User
{

    public String eid;
    public String name;
    public String email;
    public String password;

    public User() {}

    public User(String eid, String name, String email, String password)
    {
        this.eid = eid;
        this.name = name;
        this.email = email;
        this.password = password;
    }



}
