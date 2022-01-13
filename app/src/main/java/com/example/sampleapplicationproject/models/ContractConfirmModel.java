package com.example.sampleapplicationproject.models;

import java.io.Serializable;

public class ContractConfirmModel implements Serializable {
    public boolean confirmedContract;

    public boolean isConfirmedContract() {
        return confirmedContract;
    }

    public void setConfirmedContract(boolean confirmedContract) {
        this.confirmedContract = confirmedContract;
    }
}