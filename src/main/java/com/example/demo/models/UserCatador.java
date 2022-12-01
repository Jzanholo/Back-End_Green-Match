package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userCatador")
public class UserCatador {
    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();

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

    private List<String> adress = new ArrayList<>();
    @NotNull

    private List<String> materials =  new ArrayList<>();

    private List<String> dayWeek =  new ArrayList<>();

    private List<String> dayPeriod =  new ArrayList<>();

    public UserCatador(String username, String email, String password, String name, String phone, String birthDate, String gender, List<String> materials, List<String> dayWeek, List<String> dayPeriod) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
        this.gender = gender;
        this.materials = materials;
        this.dayWeek = dayWeek;
        this.dayPeriod = dayPeriod;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public void setDayWeek(List<String> dayWeek) {
        this.dayWeek = dayWeek;
    }

    public void setDayPeriod(List<String> dayPeriod) {
        this.dayPeriod = dayPeriod;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public List<String> getDayWeek() {
        return dayWeek;
    }

    public List<String> getDayPeriod() {
        return dayPeriod;
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

    public List<String> getAdress() {
        return adress;
    }

    public void setAdress(List<String> adress) {
        this.adress = adress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
