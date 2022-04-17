package com.example.nguyenngoctan_19dh110190;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String address;
    public String email;
    public String firstname;
    public String lastname;
    public double latitude;
    public double longitude;
    public String mobile;
    public String userID;

    public User() {
    }

    public User(String userID, String firstname, String lastname, String address, String email) {
        this.userID = userID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
    }



    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toString() {
        return "User{userID='" + this.userID + '\'' + ", firstname='" + this.firstname + '\'' + ", lastname='" + this.lastname + '\'' + ", address='" + this.address + '\'' + ", email='" + this.email + '\'' + '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("firstname", this.firstname);
        result.put("lastname", this.lastname);
        result.put("address", this.address);
        result.put("email", this.email);
        return result;
    }
}
