package com.example.demo.payload.requests;

import javax.validation.constraints.NotNull;

public class AddressRequest {
    private String id;
    @NotNull
    private String addressName;
   // @NotNull
    private String username;
    @NotNull
    private String street;
    @NotNull
    private int number;

    private String complement;
    @NotNull

    private String district;
    //@NotNull
    private String cep;
    @NotNull
    private String city;
    @NotNull
    private String state;


    public int getNumber() {
        return number;
    }

    public String getAddressName() {
        return addressName;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public String getCep() {
        return cep;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

}
