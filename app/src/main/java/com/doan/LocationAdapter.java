package com.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class LocationAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Location> arrayList;


    public LocationAdapter(@NonNull Context context, int resource, ArrayList<Location> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Location item = arrayList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
        }
        ImageView imgv = (ImageView) convertView.findViewById(R.id.imgLocation);
        imgv.setImageBitmap(Location.convertStringToBitmapFromAccess(context, item.getHinhAnh()) );
        TextView nameProduct = (TextView) convertView.findViewById(R.id.txtLocation);
        nameProduct.setText(item.getTenDD());

        return convertView;
    }
}