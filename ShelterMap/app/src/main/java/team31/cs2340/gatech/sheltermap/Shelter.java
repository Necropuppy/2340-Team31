package team31.cs2340.gatech.sheltermap;

import java.util.ArrayList;

/**
 * Created by Elmer on 3/3/2018.
 */

public class Shelter {

    public static ArrayList<Shelter> shelters = new ArrayList<>();

/*
    private enum gender {
        MALE,
        FEMALE,
        NONE
    }
*/

    public Shelter(String name, int key, int cap, String restrict, double longitude, double latitude,
                   String address) {
        this.name = name;
        this.key = key;
        this.capacity = cap;
        this.restriction = restrict;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    //variables
    private String name;
    private int key;
    private int capacity;
    //private gender genderType;
    private String restriction;
    private double longitude;
    private double latitude;
    private String address;

    //getters
    public String getName(){ return name; }
    public int getKey() { return key; }
    public int getCap() { return capacity; }
    //public gender getGender() { return genderType; }
    public String getRestriction(){ return restriction; }
    public double getLongitude() { return longitude; }
    public double getLatitude() { return latitude; }
    public String getAddress() { return address; }

    //setters
    public void setName(String name){ this.name = name; }
    public void setKey(int key) { this.key = key; }
    public void setCap(int cap) { capacity = cap; }
    //public void setGender(gender gen) { genderType = gen; }
    public void setGender(String restriction){ this.restriction = restriction; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setAddress(String address) { this.address = address; }
}
