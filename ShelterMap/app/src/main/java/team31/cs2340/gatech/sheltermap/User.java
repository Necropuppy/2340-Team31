package team31.cs2340.gatech.sheltermap;

import java.util.ArrayList;

/**
 * Created by Nathan on 2/20/2018.
 */

public class User {

    public static ArrayList<User> users = new ArrayList<>();

    private String name;
    private String email;
    private String password;
    private String phone;

    public User(String n, String e, String p, String ph){
        name = n;
        email = e;
        password = p;
        phone = ph;
        users.add(this);
    }

    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
