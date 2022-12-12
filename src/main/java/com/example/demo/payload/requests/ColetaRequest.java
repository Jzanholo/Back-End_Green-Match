package com.example.demo.payload.requests;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ColetaRequest {
    private String id;
    private String name_collect;
    @NotNull
    private String username;
    private String username_scavenger;
    @NotNull
    private String adress;
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

    public void setName_collect(String name_collect) {
        this.name_collect = name_collect;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username_client) {
        this.username = username;
    }

    public String getUsername_scavenger() {
        return username_scavenger;
    }

    public void setUsername_scavenger(String username_scavenger) {
        this.username_scavenger = username_scavenger;
    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public List<String> getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(List<String> dayWeek) {
        this.dayWeek = dayWeek;
    }

    public String getDayPeriod() {
        return dayPeriod;
    }

    public void setDayPeriod(String dayPeriod) {
        this.dayPeriod = dayPeriod;
    }
}
