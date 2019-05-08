package com.sinqupa.chofer;

public class Employee {
    private String username;
    private String password;
    private String latitudeTravel;
    private String longitudeTravel;
    private String email;
    private Integer code;
    private Boolean activated;

    public Employee() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
