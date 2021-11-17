package com.example.sampleapplicationproject.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.sampleapplicationproject.models.CustomAccountModel;
import com.example.sampleapplicationproject.R;

//içine koydugumuz hesabın bilgilerini bize gösteriyor

public class CustomAccountWidget extends LinearLayout {

    TextView textViewAccountNameInit;
    TextView textViewDepartmentNameInit;
    TextView textViewAccountNumberInit;
    TextView textViewBalanceInit;

    public CustomAccountWidget(Context context) {
        super(context);
        init(context, null);
    }

    public CustomAccountWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomAccountWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public CustomAccountWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_account_component, this);
        //add views
        textViewAccountNameInit = findViewById(R.id.textViewAccountNameRight);
        textViewDepartmentNameInit = findViewById(R.id.textViewDepartmentRight);
        textViewAccountNumberInit = findViewById(R.id.textViewAccountNoRight);
        textViewBalanceInit = findViewById(R.id.textViewBalanceRight);
    }

    //seçili hesabın bilgileri set account ile içine koyuyoruz.
    //textview lara secili hesabın bilgilerini basıyor
    public void setAccount(CustomAccountModel customAccount) {
        textViewAccountNameInit.setText(customAccount.getAccountName());
        textViewDepartmentNameInit.setText(customAccount.getDepartmentName());
        textViewAccountNumberInit.setText(String.valueOf(customAccount.getAccountNumber()));
        textViewBalanceInit.setText(String.valueOf(customAccount.getBalance()));
    }
}
