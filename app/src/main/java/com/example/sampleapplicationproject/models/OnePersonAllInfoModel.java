package com.example.sampleapplicationproject.models;

public class OnePersonAllInfoModel {
    String name;
    String surname;
    String birthday;
    String photo;
    String phoneNumber;
    int gender;
    String accountType;

    public OnePersonAllInfoModel(String name, String surname, String birthday, String photo, String phoneNumber, int gender, String accountType) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.photo = photo;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
