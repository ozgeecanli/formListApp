package com.example.sampleapplicationproject.ui.form;

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
import com.example.sampleapplicationproject.MockData;
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

        recyclerViewSelection.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterList = new CustomAccountListAdapter(
                MockData.getMockAccountList(),
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
        bundle.putInt(FragmentFormScreen.SCREEN_BUNDLE_KEY, Page.ACCOUNT_LIST.getPageID());

        Fragment fragmentNext = new FragmentFormScreen();
        fragmentNext.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentNext).commit();
    }
}