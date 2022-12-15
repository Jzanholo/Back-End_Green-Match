package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
public class Address {
    @Id
    private String id;
    private String addressName;
    private String username;
    private String street;
    private int number;
    private String complement;
    private String district;
    private String cep;
    private String city;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAdressName(String address_name) {
        this.addressName = address_name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String Cep) {
        this.cep = cep;
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


    @PersistenceConstructor
    public Address(String id, String addressName,String username, String street, int number, String complement, String district, String cep, String city, String state) {
        this.id = id;
        this.addressName = addressName;
        this.username = username;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.cep = cep;
        this.city = city;
        this.state = state;
    }

    @PersistenceConstructor
    public Address(String addressName,String username, String street, int number, String complement, String district, String cep, String city, String state) {
        this.addressName = addressName;
        this.username = username;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.cep = cep;
        this.city = city;
        this.state = state;
    }
}
