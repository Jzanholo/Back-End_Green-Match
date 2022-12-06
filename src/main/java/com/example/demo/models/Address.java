package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
public class Address {
    @Id
    private String id;
    private String username_user;
    private String street;
    private int complement;
    private String district;
    private String CEP;
    private String city;
    private String state;

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

    public Address(String username_user, String street, int complement, String district, String CEP, String city, String state) {
        this.username_user = username_user;
        this.street = street;
        this.complement = complement;
        this.district = district;
        this.CEP = CEP;
        this.city = city;
        this.state = state;
    }
}
