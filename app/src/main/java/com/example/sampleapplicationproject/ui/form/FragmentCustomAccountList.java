package com.example.sampleapplicationproject.ui.form;

import static com.example.sampleapplicationproject.ui.MainActivity.TAG_FORM_SCREEN;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplicationproject.CustomAccountListAdapter;
import com.example.sampleapplicationproject.OnSelectAccountListener;
import com.example.sampleapplicationproject.R;
import com.example.sampleapplicationproject.models.CustomAccountModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentCustomAccountList extends Fragment {

    @BindView(R.id.recyclerViewAccountList)
    RecyclerView recyclerViewSelection;
    CustomAccountListAdapter adapterList;
    ArrayList<CustomAccountModel> arrayListSelection;

    public static final String SELECTED_ACCOUNT = "SELECTED_ACCOUNT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_account_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        arrayListSelection = new ArrayList<>();

        recyclerViewSelection.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap",
                "Ataşehir Şubesi", 12345678, 1001));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap2",
                "Ataşehir Şubesi", 12345678, 1002));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap3",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap4",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap5",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap6",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap7",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap8",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap9",
                "Ataşehir Şubesi", 12345678, 1000));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap10",
                "Ataşehir Şubesi", 12345678, 1000));

        adapterList = new CustomAccountListAdapter(
                arrayListSelection,
                new OnSelectAccountListener() {
                    @Override
                    public void onAccountSelected(CustomAccountModel accountModel) {
                        navigateToBackScreen(accountModel);
                    }
                });
        recyclerViewSelection.setAdapter(adapterList);

    }

    private void navigateToBackScreen(CustomAccountModel accountModel) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTED_ACCOUNT, accountModel);

        Fragment fragmentNext = getActivity().getSupportFragmentManager().findFragmentByTag(TAG_FORM_SCREEN);
        fragmentNext.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentNext).commit();
    }
}