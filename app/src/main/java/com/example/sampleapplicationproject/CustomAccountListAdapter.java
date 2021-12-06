package com.example.sampleapplicationproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplicationproject.models.CustomAccountModel;

import java.util.ArrayList;

public class CustomAccountListAdapter extends RecyclerView.Adapter<CustomAccountListAdapter.ViewHolder> {

    ArrayList<CustomAccountModel> arrayList;
    OnSelectAccountListener listener;

    public CustomAccountListAdapter(ArrayList<CustomAccountModel> arrayList, OnSelectAccountListener listener) {
        this.arrayList = arrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomAccountListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_component_list, parent, false);
        return new CustomAccountListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAccountListAdapter.ViewHolder holder, int position) {
        CustomAccountModel accountModel = arrayList.get(position);
        holder.textViewAccountNameView.setText(accountModel.getAccountName());
        holder.textViewDepartmentNameView.setText(accountModel.getDepartmentName());
        holder.textViewAccountNumberView.setText(String.valueOf(accountModel.getAccountNumber()));
        holder.textViewBalanceView.setText(String.valueOf(accountModel.getBalance()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // creating variables for our views.
        private final TextView textViewAccountNameView;
        private final TextView textViewDepartmentNameView;
        private final TextView textViewAccountNumberView;
        private final TextView textViewBalanceView;

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
            if (listener != null) {
                listener.onAccountSelected(arrayList.get(getAdapterPosition()));
            }
        }
    }

}