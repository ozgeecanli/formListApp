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
import com.example.sampleapplicationproject.ui.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentCustomAccountList extends BaseFragment {

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
                "Ataşehir Şubesi", 11111111, 1001));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap2",
                "Ataşehir Şubesi2", 22222222, 1002));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap3",
                "Ataşehir Şubesi3", 33333333, 1003));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap4",
                "Ataşehir Şubesi4", 44444444, 1004));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap5",
                "Ataşehir Şubesi5", 55555555, 1005));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap6",
                "Ataşehir Şubesi6", 66666666, 1006));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap7",
                "Ataşehir Şubesi7", 77777777, 1007));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap8",
                "Ataşehir Şubesi8", 88888888, 1008));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap9",
                "Ataşehir Şubesi9", 99999999, 1009));
        arrayListSelection.add(new CustomAccountModel("Vadesiz Hesap10",
                "Ataşehir Şubesi10", 00000000, 1010));

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