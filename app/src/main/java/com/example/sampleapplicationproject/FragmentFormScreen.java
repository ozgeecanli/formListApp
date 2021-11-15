package com.example.sampleapplicationproject;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentFormScreen extends Fragment {

    // creating variables for our ui components.
    @BindView(R.id.editTextFormName)
    EditText editTextFormNameEdit;
    @BindView(R.id.editTextFormSurname)
    EditText editTextFormSurnameEdit;
    @BindView(R.id.buttonFormSave)
    Button buttonFormSaveEdit;
    @BindView(R.id.recyclerViewNameSurname)
    RecyclerView recyclerViewNameSurnameEdit;

    // variable for our adapter class and array list
    private NameSurnameAdapter adapter;
    private ArrayList<NameSurnameModal> nameSurnameModalArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View setContentView = inflater.inflate(R.layout.fragment_form_screen, container,
                false);

        ButterKnife.bind(this, setContentView);

        // calling method to load data from shared prefs.
        loadSaveData();

        // calling method to build recycler view.
        buildRecyclerView();

        // on click listener for adding and saving data to array list.
        buttonFormSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is use to add data to array list.
                nameSurnameModalArrayList.add(new NameSurnameModal(editTextFormNameEdit.getText().toString(), editTextFormSurnameEdit.getText().toString()));
                // notifying adapter when new data added.
                adapter.notifyItemInserted(nameSurnameModalArrayList.size());
            }
        });
        return setContentView;
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

    private void loadSaveData() {
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
        Type type = new TypeToken<ArrayList<NameSurnameModal>>() {
        }.getType();

        // in below line we are getting data from gson and saving it to our array list
        nameSurnameModalArrayList = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (nameSurnameModalArrayList == null) {
            // if the array list is empty
            // creating a new array list.
            nameSurnameModalArrayList = new ArrayList<>();
        }
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