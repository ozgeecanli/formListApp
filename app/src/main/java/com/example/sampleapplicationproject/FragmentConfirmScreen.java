package com.example.sampleapplicationproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sampleapplicationproject.models.OnePersonAllInfoModel;
import com.example.sampleapplicationproject.ui.BaseFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentConfirmScreen extends BaseFragment {

    @BindView(R.id.buttonSave)
    Button buttonSave;

    FormListScreenAdapter adapterFormListScreen;
    ArrayList<OnePersonAllInfoModel> arrayListOnePersonInfo;
    String name, surname, birthday, photo, phoneNumber, accountType;
    int gender;

    @OnClick(R.id.buttonSave)
    public void onClickbuttonSave() {
        saveData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_screen, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();

        name = FormScreenData.name;
        surname = FormScreenData.surname;
        birthday = FormScreenData.birthday;
        photo = FormScreenData.photo;
        phoneNumber = FormScreenData.phoneNumber;
        accountType = FormScreenData.accountType;
        gender = FormScreenData.gender;
    }

    public void insertItem(String name, String surname, String birthday, String photo,
                           String phoneNumber, int gender, String accountType) {
        adapterFormListScreen = new FormListScreenAdapter(getContext(), arrayListOnePersonInfo);
        arrayListOnePersonInfo.add(new OnePersonAllInfoModel(name, surname, birthday, photo,
                phoneNumber, gender, accountType));
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

    private void saveData() {
        insertItem(name, surname, birthday, photo, phoneNumber, gender, accountType);
        adapterFormListScreen.notifyItemInserted(arrayListOnePersonInfo.size());

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayListOnePersonInfo);
        editor.putString("PersonInfo", json);
        editor.apply();
    }
}