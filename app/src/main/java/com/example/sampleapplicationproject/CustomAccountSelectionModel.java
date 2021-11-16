package com.example.sampleapplicationproject;

public class CustomAccountSelectionModel {

    String accountNameModel;
    String departmentNameModel;
    int accountNumberModel;
    int balanceModel;

    public CustomAccountSelectionModel(String accountNameModel, String departmentNameModel, int accountNumberModel, int balanceModel) {
        this.accountNameModel = accountNameModel;
        this.departmentNameModel = departmentNameModel;
        this.accountNumberModel = accountNumberModel;
        this.balanceModel = balanceModel;
    }

    public String getAccountNameModel() {
        return accountNameModel;
    }

    public void setAccountNameModel(String accountNameModel) {
        this.accountNameModel = accountNameModel;
    }

    public String getDepartmentNameModel() {
        return departmentNameModel;
    }

    public void setDepartmentNameModel(String departmentNameModel) {
        this.departmentNameModel = departmentNameModel;
    }

    public int getAccountNumberModel() {
        return accountNumberModel;
    }

    public void setAccountNumberModel(int accountNumberModel) {
        this.accountNumberModel = accountNumberModel;
    }

    public int getBalanceModel() {
        return balanceModel;
    }

    public void setBalanceModel(int balanceModel) {
        this.balanceModel = balanceModel;
    }
}
