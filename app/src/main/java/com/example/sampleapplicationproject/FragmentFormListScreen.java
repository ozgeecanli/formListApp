package com.example.sampleapplicationproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentFormListScreen extends Fragment {

    @BindView(R.id.recyclerViewNameSurname1)
    RecyclerView recyclerViewNameSurnameEdit1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View viewFormListScreen = inflater.inflate(R.layout.fragment_form_list_screen, container,
                false);

        ButterKnife.bind(this, viewFormListScreen);

        // Inflate the layout for this fragment
        return viewFormListScreen;
    }
}