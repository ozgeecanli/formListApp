package com.example.sampleapplicationproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplicationproject.models.CustomAccountModel;
import com.example.sampleapplicationproject.ui.form.FragmentFormScreen;

import java.util.ArrayList;

public class CustomAccountAdapter extends RecyclerView.Adapter<CustomAccountAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<CustomAccountModel> arrayListCustomAccountSelectionModel;
    private Context context;

    // creating a constructor for our variables.
    public CustomAccountAdapter(ArrayList<CustomAccountModel> arrayListCustomAccountSelectionModel, Context context) {
        this.arrayListCustomAccountSelectionModel = arrayListCustomAccountSelectionModel;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_component, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAccountAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        //one by one define or
        /*
        String accountNameHolder = arrayListCustomAccountSelectionModel.get(position).getAccountNameModel();
        String departmentNameHolder = arrayListCustomAccountSelectionModel.get(position).getDepartmentNameModel();
        int accountNumberHolder = arrayListCustomAccountSelectionModel.get(position).getAccountNumberModel();
        int balanceHolder = arrayListCustomAccountSelectionModel.get(position).getBalanceModel();
        */
        CustomAccountModel modal = arrayListCustomAccountSelectionModel.get(position);


        holder.textViewAccountNameView.setText(modal.getAccountName());
        holder.textViewDepartmentNameView.setText(modal.getDepartmentName());
        holder.textViewAccountNumberView.setText(String.valueOf(modal.getAccountNumber()));
        holder.textViewBalanceView.setText(String.valueOf(modal.getBalance()));

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return arrayListCustomAccountSelectionModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // creating variables for our views.
        private TextView textViewAccountNameView, textViewDepartmentNameView, textViewAccountNumberView,
                textViewBalanceView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids on item_account_component
            textViewAccountNameView = itemView.findViewById(R.id.textViewAccountName);
            textViewDepartmentNameView = itemView.findViewById(R.id.textViewDepartmentName);
            textViewAccountNumberView = itemView.findViewById(R.id.textViewAccountNumber);
            textViewBalanceView = itemView.findViewById(R.id.textViewBalance);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Bundle bundle = new Bundle();
            CustomAccountModel modal2 = arrayListCustomAccountSelectionModel.get(position);
            bundle.putString("accountName", modal2.getAccountName());
            bundle.putString("departmentNameModel", modal2.getDepartmentName());
            bundle.putInt("accountNumber", modal2.getAccountNumber());
            bundle.putDouble("balance", modal2.getBalance());

            Fragment nextFrag = new FragmentFormScreen();
            nextFrag.setArguments(bundle);

            ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,nextFrag).commit();

        }

    }
}
