package com.example.sampleapplicationproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplicationproject.models.NameSurnameModel;

import java.util.ArrayList;

public class NameSurnameAdapter extends RecyclerView.Adapter<NameSurnameAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<NameSurnameModel> nameSurnameModalArrayList;
    private Context context;

    // creating a constructor for our variables.
    public NameSurnameAdapter(ArrayList<NameSurnameModel> nameSurnameModalArrayList, Context context) {
        this.nameSurnameModalArrayList = nameSurnameModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NameSurnameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_surname_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NameSurnameAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        NameSurnameModel modal = nameSurnameModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getCourseName());
        holder.courseDescTV.setText(modal.getCourseDescription());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return nameSurnameModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private TextView courseNameTV, courseDescTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.textViewName);
            courseDescTV = itemView.findViewById(R.id.textViewSurname);
        }
    }
}