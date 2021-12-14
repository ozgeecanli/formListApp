package com.example.sampleapplicationproject.ui.formlist;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplicationproject.FormListScreenAdapter;
import com.example.sampleapplicationproject.R;
import com.example.sampleapplicationproject.models.OnePersonAllInfoModel;
import com.example.sampleapplicationproject.ui.BaseFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentFormListScreen extends BaseFragment {

    @BindView(R.id.recyclerViewFormList)
    RecyclerView recyclerViewFormListEdit;
    FormListScreenAdapter adapterFormListScreen;
    ArrayList<OnePersonAllInfoModel> arrayListOnePersonInfo;

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
        loadData();
        buildRecyclerView();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("PersonInfo", null);
        Type type = new TypeToken<ArrayList<OnePersonAllInfoModel>>() {
        }.getType();
        arrayListOnePersonInfo = gson.fromJson(json, type);
        if (arrayListOnePersonInfo == null) {
            arrayListOnePersonInfo = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        adapterFormListScreen = new FormListScreenAdapter(getContext(), arrayListOnePersonInfo);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerViewFormListEdit.setHasFixedSize(true);
        recyclerViewFormListEdit.setLayoutManager(manager);
        recyclerViewFormListEdit.setAdapter(adapterFormListScreen);
    }
}