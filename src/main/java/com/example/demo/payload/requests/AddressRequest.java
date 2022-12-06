package com.example.demo.payload.requests;

import javax.validation.constraints.NotNull;

public class AddressRequest {
    @NotNull
    private String username_user;
    @NotNull
    private String street;
    @NotNull
    private int complement;
    @NotNull

    private String district;
    @NotNull
    private String CEP;
    @NotNull
    private String city;
    @NotNull
    private String state;

    public String getUsername_user() {
        return username_user;
    }

    public void setUsername_user(String username_user) {
        this.username_user = username_user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getComplement() {
        return complement;
    }

    public void setComplement(int complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
