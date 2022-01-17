package com.example.sampleapplicationproject.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sampleapplicationproject.LocaleHelper;
import com.example.sampleapplicationproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentHome extends BaseFragment {

    @BindView(R.id.textViewLanguage)
    TextView messageView;
    @BindView(R.id.buttonTurkish)
    Button buttonTurkish;
    @BindView(R.id.buttonEnglish)
    Button buttonEnglish;

    Context context;
    Resources resources;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = LocaleHelper.setLocale(getContext(), "en");
                resources = context.getResources();
                messageView.setText(resources.getString(R.string.language));
            }
        });

        buttonTurkish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = LocaleHelper.setLocale(getContext(), "hi");
                resources = context.getResources();
                messageView.setText(resources.getString(R.string.language));
            }
        });
    }
}