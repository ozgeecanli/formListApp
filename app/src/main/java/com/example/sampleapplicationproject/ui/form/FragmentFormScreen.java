package com.example.sampleapplicationproject.ui.form;

import static android.app.Activity.RESULT_OK;
import static com.example.sampleapplicationproject.ui.form.FragmentCustomAccountList.SELECTED_ACCOUNT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sampleapplicationproject.BundleKeys;
import com.example.sampleapplicationproject.R;
import com.example.sampleapplicationproject.models.CustomAccountModel;
import com.example.sampleapplicationproject.ui.BaseFragment;
import com.example.sampleapplicationproject.widgets.CustomAccountWidget;
import com.example.sampleapplicationproject.widgets.PhoneNumberEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentFormScreen extends BaseFragment implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.editTextFormName)
    EditText editTextFormNameEdit;
    @BindView(R.id.editTextFormSurname)
    EditText editTextFormSurnameEdit;
    @BindView(R.id.buttonBirthday)
    Button buttonBirthdayEdit;
    @BindView(R.id.textViewBirthday)
    TextView textViewBirthdayEdit;
    @BindView(R.id.imageViewProfilePhoto)
    ImageView imageViewProfilePhotoEdit;
    @BindView(R.id.customAccountView)
    CustomAccountWidget customAccountView;
    @BindView(R.id.radioButtonGender)
    RadioGroup radioGroupGender;
    @BindView(R.id.radioButtonFemale)
    RadioButton radioGroupFemale;
    @BindView(R.id.radioButtonMale)
    RadioButton radioButtonMale;
    @BindView(R.id.checkBoxAccount1)
    CheckBox checkBoxAccount1;
    @BindView(R.id.checkBoxAccount2)
    CheckBox checkBoxAccount2;
    @BindView(R.id.checkBoxAccount3)
    CheckBox checkBoxAccount3;
    @BindView(R.id.checkBoxAccount4)
    CheckBox checkBoxAccount4;
    @BindView(R.id.checkBoxAccount5)
    CheckBox checkBoxAccount5;
    @BindView(R.id.phoneNumberEditText)
    PhoneNumberEditText phoneNumberEditText;
    @BindView(R.id.checkboxContract)
    CheckBox checkboxContract;
    @BindView(R.id.buttonContinue)
    Button buttonContinue;
    @BindView(R.id.textViewAccountTypeKeep)
    TextView textViewAccountTypeKeep;

    int genderValue = -1;
    Uri uri;
    String imageStringUri = "";
    String encodedImage;
    private String imageEncodedTemp;

    public static final String SCREEN_BUNDLE_KEY = "SCREEN_BUNDLE_KEY";

    @OnClick(R.id.layoutContract)
    public void onClickCheckboxLayout() {
        FragmentContract fragmentContract = new FragmentContract();
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container, fragmentContract, null).
                addToBackStack(null).commit();
    }

    @SuppressLint("ResourceAsColor")
    @OnClick(R.id.buttonContinue)
    public void onClickButtonContinue() {
        boolean isValid = true;

        if (editTextFormNameEdit.getText().toString().equals("")) {
            isValid = false;
            editTextFormNameEdit.setHint("Lütfen adınızı giriniz");
            editTextFormNameEdit.setHintTextColor(getResources().getColor(R.color.red));
        }

        if (editTextFormSurnameEdit.getText().toString().equals("")) {
            isValid = false;
            editTextFormSurnameEdit.setHint("Lütfen soyadınızı giriniz");
            editTextFormSurnameEdit.setHintTextColor(getResources().getColor(R.color.red));
        }

        if (textViewBirthdayEdit.getText().toString().equals("")) {
            isValid = false;
            textViewBirthdayEdit.setHint("Lütfen doğum günü tarihinizi seciniz");
            textViewBirthdayEdit.setHintTextColor(getResources().getColor(R.color.red));
        }

        if (!isValid) {
            return;
        }

        Fragment fragmentConfirm = new FragmentConfirmScreen();

        Bundle bundle = new Bundle();
        bundle.putString(BundleKeys.NAME_BUNDLE_KEY, editTextFormNameEdit.getText().toString());
        bundle.putString(BundleKeys.SURNAME_BUNDLE_KEY, editTextFormSurnameEdit.getText().toString());
        bundle.putString(BundleKeys.BIRTHDAY_BUNDLE_KEY, textViewBirthdayEdit.getText().toString());
        bundle.putSerializable(BundleKeys.ACCOUNT_BUNDLE_KEY, customAccountView.getSelectedAccount());
        fragmentConfirm.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragmentConfirm);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @OnClick(R.id.imageViewProfilePhoto)
    public void onClickImageView() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            Intent photo = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(photo, 2);
        }
    }

    @OnClick(R.id.buttonBirthday)
    public void onClickButtonBirthday() {
        showDataPickerDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View setContentView = inflater.inflate(R.layout.fragment_form_screen, container,
                false);

        ButterKnife.bind(this, setContentView);

        return setContentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //click widget custom account
        customAccountView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new FragmentCustomAccountList();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, someFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //Gender selection
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonFemale:
                        genderValue = 0;
                        break;
                    case R.id.radioButtonMale:
                        genderValue = 1;
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = this.getArguments();

        if (bundle != null) {

            editTextFormNameEdit.setText(FormScreenData.name);
            editTextFormSurnameEdit.setText(FormScreenData.surname);
            textViewBirthdayEdit.setText(FormScreenData.birthday);
            phoneNumberEditText.setText(FormScreenData.phoneNumber);
            genderValue = FormScreenData.gender;

            if (genderValue == 0) {
                radioGroupFemale.setChecked(true);
            } else if (genderValue == 1) {
                radioButtonMale.setChecked(false);
            }

            checkBoxAccount1.setChecked(FormScreenData.checkBox1);
            checkBoxAccount2.setChecked(FormScreenData.checkBox2);
            checkBoxAccount3.setChecked(FormScreenData.checkBox3);
            checkBoxAccount4.setChecked(FormScreenData.checkBox4);
            checkBoxAccount5.setChecked(FormScreenData.checkBox5);

            if (FormScreenData.photo != null) {
                imageEncodedTemp = FormScreenData.photo;
                byte[] decodedString = Base64.decode(imageEncodedTemp, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageViewProfilePhotoEdit.setImageBitmap(decodedByte);
                encodedImage = FormScreenData.photo;
            }

            if (bundle.getInt(SCREEN_BUNDLE_KEY) == Page.ACCOUNT_LIST.getPageID()) {
                CustomAccountModel selectedAccount = (CustomAccountModel) bundle.getSerializable(SELECTED_ACCOUNT);
                customAccountView.setAccount(selectedAccount);
            } else if (bundle.getInt(SCREEN_BUNDLE_KEY) == Page.CONTRACTS.getPageID()) {

                if (FormScreenData.customAccountModel != null) {
                    customAccountView.setAccount(FormScreenData.customAccountModel);
                }

                checkboxContract.setChecked(bundle.getBoolean(FragmentContract.CONTRACT_IS_CHECKED_BUNDLE_KEY, false));
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        checkBoxKeep();
        FormScreenData.name = editTextFormNameEdit.getText().toString();
        FormScreenData.surname = editTextFormSurnameEdit.getText().toString();
        FormScreenData.birthday = textViewBirthdayEdit.getText().toString();
        FormScreenData.photo = encodedImage;
        FormScreenData.phoneNumber = phoneNumberEditText.getText().toString();
        FormScreenData.gender = genderValue;
        FormScreenData.checkBox1 = checkBoxAccount1.isChecked();
        FormScreenData.checkBox2 = checkBoxAccount2.isChecked();
        FormScreenData.checkBox3 = checkBoxAccount3.isChecked();
        FormScreenData.checkBox4 = checkBoxAccount4.isChecked();
        FormScreenData.checkBox5 = checkBoxAccount5.isChecked();
        FormScreenData.customAccountModel = customAccountView.getSelectedAccount();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestCode && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            imageStringUri = uri.toString();
            imageViewProfilePhotoEdit.setImageURI(uri);
            InputStream imageStream = null;
            Bitmap selectedImage;
            //ImageUri to Bitmap
            try {
                imageStream = getActivity().getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            selectedImage = BitmapFactory.decodeStream(imageStream);
            encodedImage = encodeImage(selectedImage);
        }
    }

    //Encode Bitmap in base64
    private String encodeImage(Bitmap selectedImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    //select birthday on calendar
    private void showDataPickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    //birthday textview
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "." + (month + 1) + "." + year;
        textViewBirthdayEdit.setText(date);
    }

    public void checkBoxKeep() {
        StringBuilder stringBuilder = new StringBuilder();

        if (checkBoxAccount1.isChecked()) {
            stringBuilder.append(checkBoxAccount1.getText()).append("-");
        }

        if (checkBoxAccount2.isChecked()) {
            stringBuilder.append(checkBoxAccount2.getText()).append("-");
        }

        if (checkBoxAccount3.isChecked()) {
            stringBuilder.append(checkBoxAccount3.getText()).append("-");
        }

        if (checkBoxAccount4.isChecked()) {
            stringBuilder.append(checkBoxAccount4.getText()).append("-");
        }

        if (checkBoxAccount5.isChecked()) {
            stringBuilder.append(checkBoxAccount5.getText()).append("-");
        }

        textViewAccountTypeKeep.setText(stringBuilder.toString());
        FormScreenData.accountType = textViewAccountTypeKeep.getText().toString();
    }
}