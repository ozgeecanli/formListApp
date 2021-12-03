package com.example.sampleapplicationproject.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.sampleapplicationproject.R;
import com.example.sampleapplicationproject.models.CustomAccountModel;

public class CustomAccountWidget extends LinearLayout {

    TextView textViewAccountNameInit;
    TextView textViewDepartmentNameInit;
    TextView textViewAccountNumberInit;
    TextView textViewBalanceInit;

    CustomAccountModel selectedAccount;

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

    //selected accounts info set by setAccount
    public void setAccount(CustomAccountModel customAccount) {

        if (customAccount == null) {
            return;
        }

        selectedAccount = customAccount;
        textViewAccountNameInit.setText(customAccount.getAccountName());
        textViewDepartmentNameInit.setText(customAccount.getDepartmentName());
        textViewAccountNumberInit.setText(String.valueOf(customAccount.getAccountNumber()));
        textViewBalanceInit.setText(String.valueOf(customAccount.getBalance()));
    }

    public CustomAccountModel getSelectedAccount() {
        return selectedAccount;
    }
}
