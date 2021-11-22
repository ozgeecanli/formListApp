package com.example.sampleapplicationproject.ui.form;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sampleapplicationproject.CustomAccountListAdapter;
import com.example.sampleapplicationproject.R;
import com.example.sampleapplicationproject.models.CustomAccountSelectionModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentCustomAccountList extends Fragment {

    @BindView(R.id.recyclerViewAccountList)
    RecyclerView recyclerViewSelection;
    CustomAccountListAdapter adapterList;
    ArrayList<CustomAccountSelectionModel> arrayListSelection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_account_list, container, false);

        ButterKnife.bind(this, view);

        arrayListSelection = new ArrayList<>();

        recyclerViewSelection.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayListSelection.add(new CustomAccountSelectionModel("Vadesiz Hesap",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountSelectionModel("Vadesiz Hesap2",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountSelectionModel("Vadesiz Hesap3",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountSelectionModel("Vadesiz Hesap4",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountSelectionModel("Vadesiz Hesap5",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountSelectionModel("Vadesiz Hesap6",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountSelectionModel("Vadesiz Hesap7",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountSelectionModel("Vadesiz Hesap8",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountSelectionModel("Vadesiz Hesap9",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountSelectionModel("Vadesiz Hesap10",
                "Ataşehir Şubesi", 12345678, 1000));

        adapterList = new CustomAccountListAdapter(getContext(), arrayListSelection);
        recyclerViewSelection.setAdapter(adapterList);
        return view;
    }
}