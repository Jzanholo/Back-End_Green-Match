package com.example.demo.payload.requests;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ColetaRequest {
    private String id;
    private String name_collect;
    @NotNull
    private String username;
    private String usernameScavenger;
    @NotNull
    private String address;
    private String obs;
    @NotNull
    private double weight;
    @NotNull

    private List<String> materials =  new ArrayList<>();
    @NotNull

    private List<String> dayWeek =  new ArrayList<>();
    @NotNull

    private String dayPeriod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_collect() {
        return name_collect;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username_client) {
        this.username = username;
    }

    public String getUsernameScavenger() {
        return usernameScavenger;
    }

    public String getAddress() {
        return address;
    }

    public String getObs() {
        return obs;
    }

    public double getWeight() {
        return weight;
    }

    public List<String> getMaterials() {
        return materials;
    }


    public List<String> getDayWeek() {
        return dayWeek;
    }

    public String getDayPeriod() {
        return dayPeriod;
    }

}
