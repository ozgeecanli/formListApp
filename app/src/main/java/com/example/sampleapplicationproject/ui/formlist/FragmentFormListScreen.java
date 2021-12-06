package com.example.sampleapplicationproject.ui.formlist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sampleapplicationproject.R;
import com.example.sampleapplicationproject.ui.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentFormListScreen extends BaseFragment {

    @BindView(R.id.recyclerViewFormList)
    RecyclerView recyclerViewFormListEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewFormListScreen = inflater.inflate(R.layout.fragment_form_list_screen, container,
                false);

        ButterKnife.bind(this, viewFormListScreen);

        return viewFormListScreen;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}