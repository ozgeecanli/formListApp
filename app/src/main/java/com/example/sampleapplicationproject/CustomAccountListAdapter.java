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

import com.example.sampleapplicationproject.models.CustomAccountSelectionModel;
import com.example.sampleapplicationproject.ui.form.FragmentFormScreen;

import java.util.ArrayList;

public class CustomAccountListAdapter extends RecyclerView.Adapter<CustomAccountListAdapter.ViewHolder> {

    Context context;
    ArrayList<CustomAccountSelectionModel> arrayList;

    public CustomAccountListAdapter(Context context, ArrayList<CustomAccountSelectionModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomAccountListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_component_list, parent, false);
        return new CustomAccountListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAccountListAdapter.ViewHolder holder, int position) {
        holder.textViewAccountNameView.setText(arrayList.get(position).getAccountNameSelection());
        holder.textViewDepartmentNameView.setText(arrayList.get(position).getDepartmentNameSelection());
        holder.textViewAccountNumberView.setText(String.valueOf(arrayList.get(position).getAccountNumberSelection()));
        holder.textViewBalanceView.setText(String.valueOf(arrayList.get(position).getBalanceSelection()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // creating variables for our views.
        private TextView textViewAccountNameView, textViewDepartmentNameView, textViewAccountNumberView,
                textViewBalanceView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAccountNameView = itemView.findViewById(R.id.textViewAccountNameRightList);
            textViewDepartmentNameView = itemView.findViewById(R.id.textViewDepartmentRightList);
            textViewAccountNumberView = itemView.findViewById(R.id.textViewAccountNoRightList);
            textViewBalanceView = itemView.findViewById(R.id.textViewBalanceRightList);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Bundle bundle = new Bundle();
            bundle.putString("AccountName", arrayList.get(position).getAccountNameSelection());
            bundle.putString("DepartmentName", arrayList.get(position).getDepartmentNameSelection());
            bundle.putString("AccountNumber", String.valueOf(arrayList.get(position).getAccountNumberSelection()));
            bundle.putString("Balance", String.valueOf(arrayList.get(position).getBalanceSelection()));

            Fragment fragmentNext = new FragmentFormScreen();
            fragmentNext.setArguments(bundle);

            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, fragmentNext).commit();
        }
    }
}