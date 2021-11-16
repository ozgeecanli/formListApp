package com.example.sampleapplicationproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAccountSelection extends LinearLayout {

    RecyclerView revAccount;
    CustomAccountAdapter customAccountAdapter;
    ArrayList<CustomAccountSelectionModel> accountList;

    public CustomAccountSelection(Context context) {
        super(context);
        init(context, null);
    }

    public CustomAccountSelection(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomAccountSelection(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public CustomAccountSelection(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_account_component, this);
        revAccount = findViewById(R.id.recyclerViewAccountComponent);
        accountList = new ArrayList<>();

        revAccount.setLayoutManager(new LinearLayoutManager(getContext()));
        accountList.add(new CustomAccountSelectionModel("Vadesiz Hesap",
                "Ataşehir Şubesi",12345678, 1000));
        accountList.add(new CustomAccountSelectionModel("Vadesiz Hesap2",
                "Ataşehir Şubesi",12345678, 1000));
        accountList.add(new CustomAccountSelectionModel("Vadesiz Hesap3",
                "Ataşehir Şubesi",12345678, 1000));
        accountList.add(new CustomAccountSelectionModel("Vadesiz Hesap4",
                "Ataşehir Şubesi",12345678, 1000));
        accountList.add(new CustomAccountSelectionModel("Vadesiz Hesap5",
                "Ataşehir Şubesi",12345678, 1000));
        accountList.add(new CustomAccountSelectionModel("Vadesiz Hesap6",
                "Ataşehir Şubesi",12345678, 1000));
        accountList.add(new CustomAccountSelectionModel("Vadesiz Hesap7",
                "Ataşehir Şubesi",12345678, 1000));
        accountList.add(new CustomAccountSelectionModel("Vadesiz Hesap8",
                "Ataşehir Şubesi",12345678, 1000));
        accountList.add(new CustomAccountSelectionModel("Vadesiz Hesap9",
                "Ataşehir Şubesi",12345678, 1000));
        accountList.add(new CustomAccountSelectionModel("Vadesiz Hesap10",
                "Ataşehir Şubesi",12345678, 1000));

       customAccountAdapter=new CustomAccountAdapter(accountList, context);
       revAccount.setAdapter(customAccountAdapter);
    }

}
