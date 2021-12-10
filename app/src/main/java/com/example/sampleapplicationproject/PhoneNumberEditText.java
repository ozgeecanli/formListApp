package com.example.sampleapplicationproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class PhoneNumberEditText extends LinearLayout {

    EditText editText;

    String editTextHint = "";
    int editTextNumber = 0;

    private boolean backspacingFlag = false;
    private boolean editedFlag = false;
    private int cursorComplement;

    public PhoneNumberEditText(Context context) {
        super(context);
        init(context, null);
    }

    public PhoneNumberEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PhoneNumberEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public PhoneNumberEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_phone_number, this);
        editText = findViewById(R.id.editTextPhoneNumber);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PhoneNumberEditText);
            editTextHint = a.getString(R.styleable.PhoneNumberEditText_editTextHint);
            editTextNumber = a.getInt(R.styleable.PhoneNumberEditText_editTextNumber, 0);
            a.recycle();
        }

        editText.setHint(editTextHint);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                cursorComplement = s.length() - editText.getSelectionStart();
                if (count > after) {
                    backspacingFlag = true;
                } else {
                    backspacingFlag = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                String phone = string.replaceAll("[^\\d]", "");

                if (!editedFlag) {

                    if (phone.length() >= 9 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = phone.substring(0, 1) + " (" + phone.substring(1, 4) + ") " + phone.substring(4, 7) + " " + phone.substring(7, 9) + " " + phone.substring(9);
                        editText.setText(ans);
                        editText.setSelection(editText.getText().length() - cursorComplement);

                    } else if (phone.length() >= 4 && !backspacingFlag) {
                        editedFlag = true;
                        String ans = phone.substring(0, 1) + " (" + phone.substring(1, 4) + ") " + phone.substring(4);
                        editText.setText(ans);
                        editText.setSelection(editText.getText().length() - cursorComplement);
                    }
                } else {
                    editedFlag = false;
                }
            }
        });
    }

    public Editable getText() {
        return editText.getText();
    }

    public void setText(String text) {
        editText.setText(text);
    }
}