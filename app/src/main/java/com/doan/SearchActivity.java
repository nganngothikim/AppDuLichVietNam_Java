package com.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    ImageButton btnBack;
    ListView lstv;
    ArrayList<Location> arrayList = new ArrayList<>();
    LocationAdapter locationAdapter;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        addControls();
        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        String str_search = intent.getStringExtra("TextSearch");

        dbHandler = new DBHandler(this);
        arrayList = dbHandler.getListLocation_Search(str_search);

        locationAdapter = new LocationAdapter(this, R.layout.layout_item_location, arrayList);
        lstv.setAdapter(locationAdapter);
        lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchActivity.this, Detail.class);
                intent.putExtra("MaDD", arrayList.get(i).getMaDD());
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        lstv = (ListView) findViewById(R.id.lstv);
        btnBack = (ImageButton) findViewById(R.id.btnBackDD);
    }

}