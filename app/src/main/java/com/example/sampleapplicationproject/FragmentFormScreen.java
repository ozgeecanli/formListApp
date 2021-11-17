package com.example.sampleapplicationproject;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplicationproject.models.NameSurnameModel;
import com.example.sampleapplicationproject.widgets.CustomAccountSelectionWidget;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentFormScreen extends Fragment implements DatePickerDialog.OnDateSetListener {

    // creating variables for our ui components.
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
    @BindView(R.id.buttonFormAdd)
    Button buttonFormAddEdit;
    @BindView(R.id.buttonFormSave)
    Button buttonFormSaveEdit;
    @BindView(R.id.recyclerViewNameSurname)
    RecyclerView recyclerViewNameSurnameEdit;
    @BindView(R.id.customAccountBase)
    CustomAccountSelectionWidget customAccountBase;


    // variable for our adapter class and array list
    private NameSurnameAdapter adapter;
    private ArrayList<NameSurnameModel> nameSurnameModalArrayList;

    //variable for profile photo
    private Uri imageViewProfilePhotoUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View setContentView = inflater.inflate(R.layout.fragment_form_screen, container,
                false);

        ButterKnife.bind(this, setContentView);

        // calling method to load data from shared prefs.
        loadData();

        // calling method to build recycler view.
        buildRecyclerView();

        // on click listener for adding and saving data to array list.
        buttonFormAddEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is use to add data to array list.
                nameSurnameModalArrayList.add(new NameSurnameModel(editTextFormNameEdit.getText().
                        toString(), editTextFormSurnameEdit.getText().toString()));
                // notifying adapter when new data added.
                adapter.notifyItemInserted(nameSurnameModalArrayList.size());
            }
        });

        // on click listener for saving data in shared preferences.
        buttonFormSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling method to save data in shared prefs.
                saveData();
            }
        });

        //button for birthday
        buttonBirthdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataPickerDialog();
            }
        });

        //for profile photo
        imageViewProfilePhotoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    Intent photo = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(photo, 2);
                }
            }
        });

        //add account
        customAccountBase.setAccount(new CustomAccountSelectionModel("Vadesiz Hesap",
                "Ataşehir Şubesi",12345678, 1000));

        return setContentView;
    }

    //profile photo
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent photo = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(photo, 2);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            imageViewProfilePhotoEdit.setImageURI(uri);
        }
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
        String date = "Doğum günü tarihiniz: " + dayOfMonth + "." + (month + 1) + "." + year;
        textViewBirthdayEdit.setText(date);
    }

    private void buildRecyclerView() {

        // initializing our adapter class.
        adapter = new NameSurnameAdapter(nameSurnameModalArrayList, getContext());

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        //courseRV.setHasFixedSize(true);

        // setting layout manager to our recycler view.
        recyclerViewNameSurnameEdit.setLayoutManager(manager);

        // setting adapter to our recycler view.
        recyclerViewNameSurnameEdit.setAdapter(adapter);
    }

    private void loadData() {
        // method to load arraylist from shared prefs initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = this.getActivity().
                getSharedPreferences("shared preferences", Context.MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our shared prefs if not present setting
        // it as null.
        String json = sharedPreferences.getString("courses", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<NameSurnameModel>>() {
        }.getType();

        /// in below line we are getting data from gson and saving it to our array list
        nameSurnameModalArrayList = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (nameSurnameModalArrayList == null) {
            // if the array list is empty
            // creating a new array list.
            nameSurnameModalArrayList = new ArrayList<>();
        }
    }

    private void saveData() {
        // method to load arraylist from shared prefs initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = this.getActivity().
                getSharedPreferences("shared preferences", Context.MODE_PRIVATE);

        // creating a variable for editor to store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson2 = new Gson();

        // getting data from gson and storing it in a string.
        String json2 = gson2.toJson(nameSurnameModalArrayList);

        // below line is to save data in shared prefs in the form of string.
        editor.putString("courses", json2);

        // below line is to apply changes and save data in shared prefs.
        editor.apply();

        // after saving data we are displaying a toast message.
        Toast.makeText(getActivity(), "Saved Array List to Shared preferences. ",
                Toast.LENGTH_SHORT).show();
    }

}