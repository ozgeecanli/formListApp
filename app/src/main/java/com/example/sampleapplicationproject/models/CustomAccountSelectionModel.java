package com.example.sampleapplicationproject.models;

public class CustomAccountSelectionModel {
    String accountNameSelection;
    String departmentNameSelection;
    int accountNumberSelection;
    double balanceSelection;

    public CustomAccountSelectionModel(String accountNameSelection, String departmentNameSelection, int accountNumberSelection, double balanceSelection) {
        this.accountNameSelection = accountNameSelection;
        this.departmentNameSelection = departmentNameSelection;
        this.accountNumberSelection = accountNumberSelection;
        this.balanceSelection = balanceSelection;
    }

    public CustomAccountSelectionModel() {
    }

    public String getAccountNameSelection() {
        return accountNameSelection;
    }

    public void setAccountNameSelection(String accountNameSelection) {
        this.accountNameSelection = accountNameSelection;
    }

    public String getDepartmentNameSelection() {
        return departmentNameSelection;
    }

    public void setDepartmentNameSelection(String departmentNameSelection) {
        this.departmentNameSelection = departmentNameSelection;
    }

    public int getAccountNumberSelection() {
        return accountNumberSelection;
    }

    public void setAccountNumberSelection(int accountNumberSelection) {
        this.accountNumberSelection = accountNumberSelection;
    }

    public double getBalanceSelection() {
        return balanceSelection;
    }

    public void setBalanceSelection(double balanceSelection) {
        this.balanceSelection = balanceSelection;
    }
}