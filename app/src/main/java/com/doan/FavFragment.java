package com.doan;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FavFragment extends Fragment {

    ListView lstv;
    ArrayList<Location> arrayList = new ArrayList<>();
    LocationAdapter locationAdapter;
    DBHandler dbHandler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lstv=(ListView) view.findViewById(R.id.favlst);
        dbHandler = new DBHandler(getContext());
        arrayList = dbHandler.getListLocationByUser(User.userName);
        locationAdapter = new LocationAdapter(getContext(), R.layout.layout_item_location, arrayList);
        lstv.setAdapter(locationAdapter);
        lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), Detail.class);
                intent.putExtra("MaDD", arrayList.get(i).getMaDD());
                startActivity(intent);
            }
        });
    }
}