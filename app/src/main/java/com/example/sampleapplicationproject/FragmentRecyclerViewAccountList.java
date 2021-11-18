package com.example.sampleapplicationproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sampleapplicationproject.models.CustomAccountModel;
import com.example.sampleapplicationproject.models.NameSurnameModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentRecyclerViewAccountList extends Fragment {

    @BindView(R.id.recyclerViewAccount)
    RecyclerView recyclerViewAccountEdit;

    // variable for our adapter class and array list
    private CustomAccountAdapter adapterAccount;
    private ArrayList<CustomAccountModel> AccountArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View setContentView = inflater.inflate(R.layout.fragment_recycler_view_account_list, container,
                false);
        ButterKnife.bind(this, setContentView);

        // calling method to build recycler view.
        buildRecyclerView();

        // Inflate the layout for this fragment
        return setContentView;
    }

    private void buildRecyclerView() {

        // initializing our adapter class.
        adapterAccount = new CustomAccountAdapter(AccountArrayList, getContext());

        // adding layout manager to our recycler view.
        LinearLayoutManager managerAccount = new LinearLayoutManager(getContext());
        //courseRV.setHasFixedSize(true);

        // setting layout manager to our recycler view.
        recyclerViewAccountEdit.setLayoutManager(managerAccount);

        // setting adapter to our recycler view.
        recyclerViewAccountEdit.setAdapter(adapterAccount);
    }
}