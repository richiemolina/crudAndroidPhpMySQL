package com.example.crudproyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adaptador extends ArrayAdapter<Users> {

    Context context;
    List<Users>arraylistUsers;

    public Adaptador(@NonNull Context context,List<Users>arraylistUsers ) {
        super(context,R.layout.mylistitem,arraylistUsers);


        this.context=context;
        this.arraylistUsers = arraylistUsers;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylistitem,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);

        tvID.setText(arraylistUsers.get(position).getId());
        tvName.setText(arraylistUsers.get(position).getNombre());

        return view;
    }
}
