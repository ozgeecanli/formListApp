package com.example.sampleapplicationproject.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.sampleapplicationproject.CustomAccountSelectionModel;
import com.example.sampleapplicationproject.R;

public class CustomAccountSelectionWidget extends LinearLayout {

    TextView textViewAccountNameInit;
    TextView textViewDepartmentNameInit;
    TextView textViewAccountNumberInit;
    TextView textViewBalanceInit;

    public CustomAccountSelectionWidget(Context context) {
        super(context);
        init(context, null);
    }

    public CustomAccountSelectionWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomAccountSelectionWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public CustomAccountSelectionWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_account_component, this);
        textViewAccountNameInit = findViewById(R.id.textViewAccountName);
        textViewDepartmentNameInit = findViewById(R.id.textViewDepartmentName);
        textViewAccountNumberInit = findViewById(R.id.textViewAccountNumber);
        textViewBalanceInit = findViewById(R.id.textViewBalance);
    }

    public void setAccount(CustomAccountSelectionModel customAccount) {
        textViewAccountNameInit.setText(customAccount.getAccountName());
        textViewDepartmentNameInit.setText(customAccount.getDepartmentName());
        textViewAccountNumberInit.setText(String.valueOf(customAccount.getAccountNumber()));
        textViewBalanceInit.setText(String.valueOf(customAccount.getBalance()));
    }
}
