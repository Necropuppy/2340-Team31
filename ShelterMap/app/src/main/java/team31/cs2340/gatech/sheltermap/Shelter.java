package team31.cs2340.gatech.sheltermap;

/**
 * Created by siddudussa on 3/4/18.
 */

class Shelter {

    private int uniqueKey;
    private String name;
    private String capacity;
    private String restrictions;
    private double longitude;
    private double latitude;
    private String address;
    private String notes;
    private String phone;

    public Shelter() {
        this(0, "N/A", "N/A", "N/A", 0, 0, "N/A", "N/A", "N/A");
    }

    public Shelter(int u, String na, String c, String r, double lo, double la, String a, String no, String p) {
        uniqueKey = u;
        name = na;
        capacity = c;
        restrictions = r;
        longitude = lo;
        latitude = la;
        address = a;
        notes = no;
        phone = p;
    }

    public int getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(int u) {
        uniqueKey = u;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String c) {
        capacity = c;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String r) {
        restrictions = r;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double lo) {
        longitude = lo;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double la) {
        latitude = la;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String a) {
        address = a;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String n) {
        notes = n;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String p) {
        phone = p;
    }

    public String toString() {
        return uniqueKey + " - " + name;
    }

}
