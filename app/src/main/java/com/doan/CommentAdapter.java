package com.doan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CommentAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Comment> arrayList;


    public CommentAdapter(@NonNull Context context, int resource, ArrayList<Comment> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Comment item = arrayList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
        }
        TextView user = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView cmt = (TextView) convertView.findViewById(R.id.tvBinhLuan);
        TextView thoiGian = (TextView) convertView.findViewById(R.id.tvThoiGian);
        user.setText(item.getUser());
        cmt.setText(item.getCmt());
        thoiGian.setText(item.getTg());
        return convertView;
    }
}
