package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Document(collection = "Coleta Solicitada")
public class ColetaSolicitada {
    @Id
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

    public ColetaSolicitada(String username) {
        this.username = username;
    }

    @PersistenceConstructor
    public ColetaSolicitada(String name_collect,String username, String address, String obs, double weight, List<String> materials, List<String> dayWeek, String dayPeriod) {
        this.name_collect = name_collect;
        this.username = username;
        this.address = address;
        this.obs = obs;
        this.weight = weight;
        this.materials = materials;
        this.dayWeek = dayWeek;
        this.dayPeriod = dayPeriod;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameScavenger() {
        return usernameScavenger;
    }

    public void setUsernameScavenger(String username_scavenger) {
        this.usernameScavenger = username_scavenger;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
