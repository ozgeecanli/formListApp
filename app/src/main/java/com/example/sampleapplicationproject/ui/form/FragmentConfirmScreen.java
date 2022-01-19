package com.example.sampleapplicationproject.ui.form;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleapplicationproject.BundleKeys;
import com.example.sampleapplicationproject.FormListScreenAdapter;
import com.example.sampleapplicationproject.R;
import com.example.sampleapplicationproject.models.CustomAccountModel;
import com.example.sampleapplicationproject.models.OnePersonAllInfoModel;
import com.example.sampleapplicationproject.ui.BaseFragment;
import com.example.sampleapplicationproject.widgets.CustomAccountWidget;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentConfirmScreen extends BaseFragment {

    @BindView(R.id.textViewConfirmNameEdit)
    TextView textViewConfirmNameEdit;
    @BindView(R.id.textViewConfirmSurnameEdit)
    TextView textViewConfirmSurnameEdit;
    @BindView(R.id.textViewConfirmBirthdayEdit)
    TextView textViewConfirmBirthdayEdit;
    @BindView(R.id.imageViewConfirmPhotoEdit)
    ImageView imageViewConfirmPhotoEdit;
    @BindView(R.id.textViewConfirmPhoneNumberEdit)
    TextView textViewConfirmPhoneNumberEdit;
    @BindView(R.id.textViewConfirmGenderEdit)
    TextView textViewConfirmGenderEdit;
    @BindView(R.id.textViewConfirmAccountEdit)
    TextView textViewConfirmAccountEdit;
    @BindView(R.id.CustomAccountViewConfirm)
    CustomAccountWidget customAccountView;
    @BindView(R.id.buttonReturn)
    Button buttonReturn;
    @BindView(R.id.buttonSave)
    Button buttonSave;

    FormListScreenAdapter adapterFormListScreen;
    ArrayList<OnePersonAllInfoModel> arrayListOnePersonInfo;
    String name, surname, birthday, photo, phoneNumber, accountType;
    int gender;

    @OnClick(R.id.buttonSave)
    public void onClickButtonSave() {
        saveData();
    }

    @OnClick(R.id.buttonReturn)
    public void onClickButtonReturn() {

        FragmentFormScreen fragmentFormScreen = new FragmentFormScreen();
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container, fragmentFormScreen, null).
                commit();
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

        name = getArguments().getString(BundleKeys.NAME_BUNDLE_KEY);
        surname = getArguments().getString(BundleKeys.SURNAME_BUNDLE_KEY);
        birthday = getArguments().getString(BundleKeys.BIRTHDAY_BUNDLE_KEY);

        photo = FormScreenData.photo;
        phoneNumber = FormScreenData.phoneNumber;
        accountType = FormScreenData.accountType;
        gender = FormScreenData.gender;

        textViewConfirmNameEdit.setText(name);
        textViewConfirmSurnameEdit.setText(surname);
        textViewConfirmBirthdayEdit.setText(birthday);

        if (photo != null) {
            byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageViewConfirmPhotoEdit.setImageBitmap(decodedByte);
        } else {
            imageViewConfirmPhotoEdit.setVisibility(View.GONE);
        }

        textViewConfirmPhoneNumberEdit.setText(phoneNumber);

        if (gender == 0) {
            textViewConfirmGenderEdit.setText("@string/female");

        } else {
            textViewConfirmGenderEdit.setText("Erkek");
        }

        textViewConfirmAccountEdit.setText(accountType);

        CustomAccountModel accountModel = (CustomAccountModel) getArguments().getSerializable(BundleKeys.ACCOUNT_BUNDLE_KEY);
        if (accountModel != null) {
            customAccountView.setAccount(accountModel);
        }
    }

    public void insertItem(String name, String surname, String birthday, String photo,
                           String phoneNumber, int gender, String accountType) {
        adapterFormListScreen = new FormListScreenAdapter(getContext(), arrayListOnePersonInfo, onePersonAllInfoModel -> {
            Toast.makeText(getContext(), "" + onePersonAllInfoModel.getName() + " " + onePersonAllInfoModel.getSurname() + " " + onePersonAllInfoModel.getBirthday(), Toast.LENGTH_SHORT).show();
        });
        arrayListOnePersonInfo.add(new OnePersonAllInfoModel(name, surname, birthday, photo,
                phoneNumber, gender, accountType));
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