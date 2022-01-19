package com.example.sampleapplicationproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplicationproject.models.OnePersonAllInfoModel;

import java.util.ArrayList;

public class FormListScreenAdapter extends RecyclerView.Adapter<FormListScreenAdapter.ViewHolder> {

    Context context;
    ArrayList<OnePersonAllInfoModel> arrayList;
    FormListScreenItemSelectedListener formListScreenItemSelectedListener;

    public interface FormListScreenItemSelectedListener {
        void onSelected(OnePersonAllInfoModel onePersonAllInfoModel);
    }

    public FormListScreenAdapter(Context context, ArrayList<OnePersonAllInfoModel> arrayList, FormListScreenItemSelectedListener formListScreenItemSelectedListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.formListScreenItemSelectedListener = formListScreenItemSelectedListener;
    }

    @NonNull
    @Override
    public FormListScreenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_all_info,
                parent, false);

        return new FormListScreenAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormListScreenAdapter.ViewHolder holder, int position) {
        String name = arrayList.get(position).getName();
        String surname = arrayList.get(position).getSurname();
        int gender = arrayList.get(position).getGender();
        String photo = arrayList.get(position).getPhoto();

        holder.textViewNameAndSurname.setText(name + " " + surname);

        if (gender == 0) {
            int femaleColor = Color.parseColor("#FFBB86FC");
            holder.relativeLayoutItemPerson.setBackgroundColor(femaleColor);
        } else {
            int maleColor = Color.parseColor("#B7E4E0");
            holder.relativeLayoutItemPerson.setBackgroundColor(maleColor);
        }

        byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.imageViewPhoto.setImageBitmap(decodedByte);
        holder.itemView.setOnClickListener(view -> formListScreenItemSelectedListener.onSelected(arrayList.get(position)));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewPhoto;
        TextView textViewNameAndSurname;
        RelativeLayout relativeLayoutItemPerson;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
            textViewNameAndSurname = itemView.findViewById(R.id.textViewNameAndSurname);
            relativeLayoutItemPerson = itemView.findViewById(R.id.relativeLayoutItemPerson);
        }
    }
}