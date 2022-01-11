package com.example.sampleapplicationproject;

import com.example.sampleapplicationproject.models.CustomAccountModel;

import java.util.ArrayList;

public class MockData {

    public static ArrayList<CustomAccountModel> getMockAccountList() {
        ArrayList<CustomAccountModel> arrayListSelection = new ArrayList<>();
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap", "Ataşehir Şubesi", 11111111, 1001));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap2",
                "Ataşehir Şubesi2", 22222222, 1002));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap3",
                "Ataşehir Şubesi3", 33333333, 1003));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap4",
                "Ataşehir Şubesi4", 44444444, 1004));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap5",
                "Ataşehir Şubesi5", 55555555, 1005));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap6",
                "Ataşehir Şubesi6", 66666666, 1006));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap7",
                "Ataşehir Şubesi7", 77777777, 1007));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap8",
                "Ataşehir Şubesi8", 88888888, 1008));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap9",
                "Ataşehir Şubesi9", 99999999, 1009));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap10",
                "Ataşehir Şubesi10", 00000000, 1010));
        return arrayListSelection;
    }

}
