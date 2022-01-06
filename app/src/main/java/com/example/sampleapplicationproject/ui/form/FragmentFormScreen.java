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
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sampleapplicationproject.FragmentConfirmScreen;
import com.example.sampleapplicationproject.R;
import com.example.sampleapplicationproject.models.CustomAccountModel;
import com.example.sampleapplicationproject.ui.BaseFragment;
import com.example.sampleapplicationproject.widgets.CustomAccountWidget;
import com.example.sampleapplicationproject.widgets.PhoneNumberEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
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
    @BindView(R.id.textViewContract)
    TextView textViewContract;
    @BindView(R.id.buttonContinue)
    Button buttonContinue;
    @BindView(R.id.textViewAccountTypeKeep)
    TextView textViewAccountTypeKeep;

    String ContractText = "Sözleşmeyi okudum ve onaylıyorum.";
    boolean checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    int genderValue = -1;
    ArrayList<String> arrayListCheckBox;
    Uri uri;
    private static final int preqCode = 1;
    private static final int requestCode = 1;
    String imageStringUri = "";
    String encodedImage;
    private String imageEncodedTemp;

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

        if(!isValid){
            return;
        }

        Fragment fragmentConfirm = new FragmentConfirmScreen();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragmentConfirm);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @OnClick(R.id.checkBoxAccount1)
    public void checkBoxAccount1OnClick() {
        if (checkBoxAccount1.isChecked()) {
            arrayListCheckBox.add(checkBoxAccount1.getText().toString());
            checkBox1 = true;
        } else {
            arrayListCheckBox.remove(checkBoxAccount1.getText().toString());
            checkBox1 = false;
        }
    }

    @OnClick(R.id.checkBoxAccount2)
    public void checkBoxAccount2OnClick() {
        if (checkBoxAccount2.isChecked()) {
            arrayListCheckBox.add(checkBoxAccount2.getText().toString());
            checkBox2 = true;
        } else {
            arrayListCheckBox.remove(checkBoxAccount2.getText().toString());
            checkBox2 = false;
        }
    }

    @OnClick(R.id.checkBoxAccount3)
    public void checkBoxAccount3OnClick() {
        if (checkBoxAccount3.isChecked()) {
            arrayListCheckBox.add(checkBoxAccount3.getText().toString());
            checkBox3 = true;
        } else {
            arrayListCheckBox.remove(checkBoxAccount3.getText().toString());
            checkBox3 = false;
        }
    }

    @OnClick(R.id.checkBoxAccount4)
    public void checkBoxAccount4OnClick() {
        if (checkBoxAccount4.isChecked()) {
            arrayListCheckBox.add(checkBoxAccount4.getText().toString());
            checkBox4 = true;
        } else {
            arrayListCheckBox.remove(checkBoxAccount4.getText().toString());
            checkBox4 = false;
        }
    }

    @OnClick(R.id.checkBoxAccount5)
    public void checkBoxAccount5OnClick() {
        if (checkBoxAccount5.isChecked()) {
            arrayListCheckBox.add(checkBoxAccount5.getText().toString());
            checkBox5 = true;
        } else {
            arrayListCheckBox.remove(checkBoxAccount5.getText().toString());
            checkBox5 = false;
        }
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

    @OnClick(R.id.textViewContract)
    public void onClickTextViewContract() {
        contractClickable();
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

        //create arraylist for check account
        arrayListCheckBox = new ArrayList<>();

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
        /*
        //returns account selected from custom account list to fragment form screen
        if (bundle != null && bundle.getInt("confirmContract") == 0) {
            CustomAccountModel selectedAccount = (CustomAccountModel) bundle.getSerializable(SELECTED_ACCOUNT);
            customAccountView.setAccount(selectedAccount);
        }
         */
        if (bundle != null) {
            editTextFormNameEdit.setText(FormScreenData.name);
            editTextFormSurnameEdit.setText(FormScreenData.surname);
            textViewBirthdayEdit.setText(FormScreenData.birthday);

            if (bundle != null && bundle.getInt("confirmContract") == 0) {
                CustomAccountModel selectedAccount = (CustomAccountModel) bundle.getSerializable(SELECTED_ACCOUNT);
                customAccountView.setAccount(selectedAccount);
            } else if (FormScreenData.customAccountModel != null) {
                customAccountView.setAccount(FormScreenData.customAccountModel);
            }

            if (FormScreenData.photo != null) {
                imageEncodedTemp = FormScreenData.photo;
                byte[] decodedString = Base64.decode(imageEncodedTemp, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageViewProfilePhotoEdit.setImageBitmap(decodedByte);
                encodedImage = FormScreenData.photo;
            }

            phoneNumberEditText.setText(FormScreenData.phoneNumber);
            genderValue = FormScreenData.gender;
            if (genderValue == 0) {
                radioGroupFemale.setChecked(true);
            } else if (genderValue == 1) {
                radioButtonMale.setChecked(false);
            }
            if (FormScreenData.checkBox1) {
                checkBoxAccount1.setChecked(true);
                checkBoxAccount1OnClick();
            }
            if (FormScreenData.checkBox2) {
                checkBoxAccount2.setChecked(true);
                checkBoxAccount2OnClick();
            }
            if (FormScreenData.checkBox3) {
                checkBoxAccount3.setChecked(true);
                checkBoxAccount3OnClick();
            }
            if (FormScreenData.checkBox4) {
                checkBoxAccount4.setChecked(true);
                checkBoxAccount4OnClick();
            }
            if (FormScreenData.checkBox5) {
                checkBoxAccount5.setChecked(true);
                checkBoxAccount5OnClick();
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
        FormScreenData.checkBox1 = checkBox1;
        FormScreenData.checkBox2 = checkBox2;
        FormScreenData.checkBox3 = checkBox3;
        FormScreenData.checkBox4 = checkBox4;
        FormScreenData.checkBox5 = checkBox5;
        // FormScreenData.customAccountModel bos geliyor
        FormScreenData.customAccountModel = customAccountView.getSelectedAccount();
    }

    //profile photo
    private void checkAndRequestForPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(getContext(), "Lütfen uygulama ayarlarından gerekli izinleri açınız", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, preqCode);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, preqCode);
            }
        } else {
            Intent intentImage = new Intent(Intent.ACTION_GET_CONTENT);
            intentImage.setType("image/*");
            startActivityForResult(intentImage, requestCode);
        }
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

    //contract
    public void contractClickable() {
        SpannableString spannableString = new SpannableString(ContractText);
        ClickableSpan clickableSpan = new ClickableSpan() {

            @Override
            public void onClick(View widget) {
                FragmentContract fragmentContract = new FragmentContract();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container, fragmentContract, null).
                        addToBackStack(null).commit();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };
        spannableString.setSpan(clickableSpan, 0, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewContract.setText(spannableString);
        textViewContract.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void checkBoxKeep() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : arrayListCheckBox) {
            stringBuilder.append(string).append("-");
            textViewAccountTypeKeep.setText(stringBuilder.toString());
        }
        FormScreenData.accountType = textViewAccountTypeKeep.getText().toString();
    }
}