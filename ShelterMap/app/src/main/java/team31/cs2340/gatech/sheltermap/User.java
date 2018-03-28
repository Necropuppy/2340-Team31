package team31.cs2340.gatech.sheltermap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nathan on 2/20/2018.
 */

public class User {

    public static ArrayList<User> users = new ArrayList<>();
    public static int Reserved = 0;
    public static int ResNum = 0;
    public static int ShelterId = -1;

    public static final List<String> legalTypes = Arrays.asList("Admin", "Shelter Worker", "Shelter Seeker");

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
