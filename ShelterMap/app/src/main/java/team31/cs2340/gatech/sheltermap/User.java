package team31.cs2340.gatech.sheltermap;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*
 * Created by Nathan on 2/20/2018.
 */

/**
 * Class for User object (contains all infortmation for user objects)
 */
public class User {

    static Collection<User> users = new ArrayList<>();
    //public static int Reserved = 0;
    //public static int ResNum = 0;
    //public static int ShelterId = -1;
    static User currentUser;
    static boolean annonymous = false;

    public static final List<String> legalTypes = Arrays.asList("Admin", "Shelter Worker",
            "Shelter Seeker");

    /**
     * Load users from database
     * @param context where database located
     */
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
                new User(tokens[0],tokens[1],tokens[2],tokens[3],Integer.parseInt(tokens[4]),
                        Integer.parseInt(tokens[5]),Integer.parseInt(tokens[6]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Saves users into database
     * @param context where database located
     */
    public static void saveUsers(Context context) {
        String fileName = "UsersDatabase";
        String fileContents = "";
        FileOutputStream outputStream;

        File directory = context.getFilesDir();
        File file = new File(directory, fileName);
        file.delete();



        for (User u:users) {
            fileContents += u.getName() + "," + u.getEmail() + "," + u.getPassword() + "," +
                    u.getPhone() + "," + u.getUserReserved() + "," + u.getUserResNum() + ","
                    + u.getUserShelterId() + "\n";
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

    /**
     * Simple constructor of User
     * @param n name of user
     * @param e email of user
     * @param p password of user
     * @param ph phone of user
     */
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

    /**
     * Constructor for User with extra params for reservations
     * @param n name of user
     * @param e email of user
     * @param p password of user
     * @param ph phone of user
     * @param ur whether user reserved
     * @param urn number of people user reserved
     * @param usi shelter id for where user reserved
     */
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

    /**
     * Returns name of user object
     * @return name of user object
     */
    public String getName(){
        return name;
    }

    /**
     * Returns email of user object
     * @return email of user object
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns password of user object
     * @return password of user object
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns number of reserved shelters of user
     * @return number of reserved shelters of user
     */
    public int getUserReserved() {
        return UserReserved;
    }

    /**
     * Returns number of people user reserved for
     * @return number of people user reserved for
     */
    public int getUserResNum() {
        return UserResNum;
    }

    /**
     * Returns the shelter id user reserved for
     * @return shelter id user reserved for
     */
    public int getUserShelterId() {
        return UserShelterId;
    }

    /**
     * Returns phone of user
     * @return phone of user
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the users reserved value
     * @param ur int representing whether user has reserved
     */
    public void setUserReserved(int ur) {
        UserReserved = ur;
    }

    /**
     * Sets the users number of reserved spots
     * @param urs ints representing number of reserved spots
     */
    public void setUserResNum(int urs) {
        UserResNum = urs;
    }

    /**
     * Sets the users shelter id where user reserved
     * @param usi int representing shelter id where user reserved
     */
    public void setUserShelterId(int usi) {
        UserShelterId = usi;
    }
}
