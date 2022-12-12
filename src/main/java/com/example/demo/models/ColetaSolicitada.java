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

    public ColetaSolicitada(String username) {
        this.username = username;
    }

    @PersistenceConstructor
    public ColetaSolicitada(String name_collect,String username, String username_scavenger, String adress, String obs, double weight, List<String> materials, List<String> dayWeek, String dayPeriod) {
        this.name_collect = name_collect;
        this.username = username;
        this.username_scavenger = username_scavenger;
        this.adress = adress;
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
