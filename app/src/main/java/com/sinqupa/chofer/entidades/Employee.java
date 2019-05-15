package com.sinqupa.chofer.entidades;

public class Employee {
    private String userID;
    private double latitudeTravel;
    private double longitudeTravel;
    private Integer code;
    private Boolean activated;

    public Employee() {
    }

    public Employee(String userID, double latitudeTravel, double longitudeTravel, Integer code, Boolean activated) {
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

    public double getLatitudeTravel() {
        return latitudeTravel;
    }

    public void setLatitudeTravel(double latitudeTravel) {
        this.latitudeTravel = latitudeTravel;
    }

    public double getLongitudeTravel() {
        return longitudeTravel;
    }

    public void setLongitudeTravel(double longitudeTravel) {
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
