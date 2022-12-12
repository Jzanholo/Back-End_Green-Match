package com.example.demo.payload.response;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ColectInfoResponse {
    private String id;
    private String adress;
    private String obs;
    private double weight;
    private List<String> materials =  new ArrayList<>();
    private List<String> dayWeek =  new ArrayList<>();
    private List<String> dayPeriod =  new ArrayList<>();

    public ColectInfoResponse(String id, String adress, String obs, double weight, List<String> materials, List<String> dayWeek, List<String> dayPeriod) {
        this.id = id;
        this.adress = adress;
        this.obs = obs;
        this.weight = weight;
        this.materials = materials;
        this.dayWeek = dayWeek;
        this.dayPeriod = dayPeriod;
    }
}
