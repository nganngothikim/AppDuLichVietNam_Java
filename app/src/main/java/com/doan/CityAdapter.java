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

public class CityAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<City> arrayList;

    public CityAdapter(@NonNull Context context, int resource, ArrayList<City> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City item = arrayList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
        }
        ImageView imgv = (ImageView) convertView.findViewById(R.id.imgCity);
        imgv.setImageBitmap(City.convertStringToBitmapFromAccess(context, item.getHinhAnh()));
        TextView nameProduct = (TextView) convertView.findViewById(R.id.txtCity);
        nameProduct.setText(item.getTenTinh());
        return convertView;
    }
}