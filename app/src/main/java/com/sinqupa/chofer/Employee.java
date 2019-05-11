package com.sinqupa.chofer;

public class Employee {
    private String userID;
    private String latitudeTravel;
    private String longitudeTravel;
    private Integer code;
    private Boolean activated;

    public Employee() {
    }

    public Employee(String userID, String latitudeTravel, String longitudeTravel, Integer code, Boolean activated) {
        this.userID = userID;
        this.latitudeTravel = latitudeTravel;
        this.longitudeTravel = longitudeTravel;
        this.code = code;
        this.activated = activated;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLatitudeTravel() {
        return latitudeTravel;
    }

    public void setLatitudeTravel(String latitudeTravel) {
        this.latitudeTravel = latitudeTravel;
    }

    public String getLongitudeTravel() {
        return longitudeTravel;
    }

    public void setLongitudeTravel(String longitudeTravel) {
        this.longitudeTravel = longitudeTravel;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}
