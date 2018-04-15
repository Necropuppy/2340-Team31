package team31.cs2340.gatech.sheltermap;

import java.util.ArrayList;

/*
 * Created by Elmer on 3/3/2018.
 */

/**
 * Class for Shelter object (contains all information for shelter objects)
 */
public class Shelter extends RuntimeException{

    static ArrayList<Shelter> shelters = new ArrayList<>();

/*
    private enum gender {
        MALE,
        FEMALE,
        NONE
    }
*/

    /**
     * Constructor for shelter object
     * @param name name of shelter
     * @param key shelter key
     * @param cap shelter capacity
     * @param restrict shelter restrictions
     * @param longitude shelter longitude
     * @param latitude shelter latitude
     * @param address shelter address
     */
    public Shelter(String name, int key, int cap, String restrict, double longitude,
                   double latitude, String address) {
        this.name = name;
        this.key = key;
        this.capacity = cap;
        this.restriction = restrict;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        pop = 0;
        if (User.users.size() > 0) {
            for (User u : User.users) {
                if (u.getUserShelterId() == this.key) {
                    pop += u.getUserResNum();//addPop(u.getUserResNum());
                    if (pop > this.capacity) {
                        throw new RuntimeException("Population greater than capacity");
                    }
                }
            }
        }
    }

    //variables
    private String name;
    private int key;
    private int capacity;
    private int pop;
    //private gender genderType;
    private String restriction;
    private double longitude;
    private double latitude;
    private String address;

    //getters

    /**
     * Returns name of shelter
     * @return name of shelter
     */
    public String getName(){ return name; }

    /**
     * Returns key of shelter
     * @return key of shelter
     */
    public int getKey() { return key; }

    /**
     * Returns capacity of shelter
     * @return capacity of shelter
     */
    public int getCap() { return capacity; }

    /**
     * Returns population of shelter
     * @return pop of shelter
     */
    public int getPop() {return pop;}
    //public gender getGender() { return genderType; }

    /**
     * Returns restriction of shelter
     * @return restrictions of shelter
     */
    public String getRestriction(){ return restriction; }

    /**
     * Returns longitude of shelter
     * @return longitude of shelter
     */
    public double getLongitude() { return longitude; }

    /**
     * Returns latitude of shelter
     * @return latitude of shelter
     */
    public double getLatitude() { return latitude; }

    /**
     * Returns address of shelter
     * @return address of shelter
     */
    public String getAddress() { return address; }

    //setters

    /**
     * Set shelter name
     * @param name name of shelter
     */
    public void setName(String name){ this.name = name; }

    /**
     * Set shelter key
     * @param key key of shelter
     */
    public void setKey(int key) { this.key = key; }

    /**
     * Set shelter cap
     * @param cap capacity
     */
    public void setCap(int cap) { capacity = cap; }

    /**
     * Add i to the population of shelter
     * @param i number of new reserved
     */
    public void addPop(int i){pop+=i;}

    /**
     * Subtract i to the population of shelter
     * @param i number of unreserved
     */
    public void subPop(int i){pop-=i;}
    //public void setGender(gender gen) { genderType = gen; }

    /**
     * Set restriction on shelter
     * @param restriction restriction of shelter
     */
    public void setGender(String restriction){ this.restriction = restriction; }

    /**
     * Set longitutde of shelter
     * @param longitude longitutde of shelter
     */
    public void setLongitude(double longitude) { this.longitude = longitude; }

    /**
     * Set latitude of shelter
     * @param latitude latitude of shelter
     */
    public void setLatitude(double latitude) { this.latitude = latitude; }

    /**
     * Set address of shelter
     * @param address address of shelter
     */
    public void setAddress(String address) { this.address = address; }
}
