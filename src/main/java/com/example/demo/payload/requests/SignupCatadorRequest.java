package com.example.demo.payload.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SignupCatadorRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> roles;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(max = 120)
    private String name;

    @NotBlank
    @Size(max = 11)
    private String phone;
    @NotBlank
    @Size(max = 10)
    private String birthDate;
    @NotBlank
    @Size(max = 9)
    private String gender;

    @NotBlank
    private String work;

    @NotNull
    private List<String> materials =  new ArrayList<>();

    private List<String> dayWeek =  new ArrayList<>();

    private List<String> dayPeriod =  new ArrayList<>();

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
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

    public List<String> getDayPeriod() {
        return dayPeriod;
    }

    public void setDayPeriod(List<String> dayPeriod) {
        this.dayPeriod = dayPeriod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRole(Set<String> roles) {
        this.roles = roles;
    }
}
