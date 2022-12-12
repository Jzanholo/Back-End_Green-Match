package com.example.demo.payload.requests;

import javax.validation.constraints.NotNull;

public class AddressRequest {
    @NotNull
    private String address_name;
   // @NotNull
    private String username;
    @NotNull
    private String street;
    @NotNull
    private String complement;
    @NotNull

    private String district;
    //@NotNull
    private String cep;
    @NotNull
    private String city;
    @NotNull
    private String state;

    public String getAddress_name() {
        return address_name;
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
