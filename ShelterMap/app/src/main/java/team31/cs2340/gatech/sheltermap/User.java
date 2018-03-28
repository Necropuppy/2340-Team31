package team31.cs2340.gatech.sheltermap;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nathan on 2/20/2018.
 */

public class User {

    public static ArrayList<User> users = new ArrayList<>();
    //public static int Reserved = 0;
    //public static int ResNum = 0;
    //public static int ShelterId = -1;
    public static User currentUser;

    public static final List<String> legalTypes = Arrays.asList("Admin", "Shelter Worker", "Shelter Seeker");

    public static void loadUsers(Context context) {
        String fileName = "UsersDatabase";
        File directory = context.getFilesDir();
        users.clear();



        FileInputStream fis = null;
        try {
            fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String [] tokens = line.split(",");
                new User(tokens[0],tokens[1],tokens[2],tokens[3],Integer.parseInt(tokens[4]),Integer.parseInt(tokens[5]),Integer.parseInt(tokens[6]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void saveUsers(Context context) {
        String fileName = "UsersDatabase";
        String fileContents = "";
        FileOutputStream outputStream;

        File directory = context.getFilesDir();
        File file = new File(directory, fileName);
        file.delete();



        for (User u:users) {
            fileContents += u.getName() + "," + u.getEmail() + "," + u.getPassword() + "," +
                    u.getPhone() + "," + u.getUserReserved() + "," + u.getUserResNum() + "," + u.getUserShelterId() + "\n";
        }

        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String name;
    private String email;
    private String password;
    private String phone;
    private int UserReserved;
    private int UserResNum;
    private int UserShelterId;

    public User(String n, String e, String p, String ph){
        name = n;
        email = e;
        password = p;
        phone = ph;
        UserReserved = 0;
        UserResNum = 0;
        UserShelterId = -1;
        users.add(this);
    }

    public User(String n, String e, String p, String ph, int ur,int urn, int usi){
        name = n;
        email = e;
        password = p;
        phone = ph;
        UserReserved = ur;
        UserResNum = urn;
        UserShelterId = usi;
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

    public int getUserReserved() {
        return UserReserved;
    }
    public int getUserResNum() {
        return UserResNum;
    }

    public int getUserShelterId() {
        return UserShelterId;
    }

    public String getPhone() {
        return phone;
    }

    public void setUserReserved(int ur) {
        UserReserved = ur;
    }
    public void setUserResNum(int urs) {
        UserResNum = urs;
    }

    public void setUserShelterId(int usi) {
        UserShelterId = usi;
    }
}
