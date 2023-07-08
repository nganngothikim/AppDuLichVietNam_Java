package com.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Fragment f;
    Toolbar toolbar;
    ImageButton imgbtnSearch;
    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addContronls();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        f= new HomeFragment();
        loadFragment(f);


        imgbtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtSearch.getText().toString().isEmpty())
                    Toast.makeText(MainActivity.this, "Vui lòng nhập từ khóa tìm kiếm", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("TextSearch", edtSearch.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }
    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    getSupportActionBar().hide();
                    f= new Fragment_Pro();
                    loadFragment(f);
                    return true;
                case R.id.navigation_home:
                    getSupportActionBar().show();
                    f= new HomeFragment();
                    loadFragment(f);
                    return true;
                case R.id.navigation_dashboard:
                    getSupportActionBar().hide();
                    f= new FavFragment();
                    loadFragment(f);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment k)
    {
        FragmentTransaction tra = getSupportFragmentManager().beginTransaction();
        tra.replace(R.id.frame_container, k);
        tra.addToBackStack(null);
        tra.commit();
    }

    void addContronls()
    {
        toolbar = findViewById(R.id.toolbar);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        imgbtnSearch = (ImageButton) findViewById(R.id.imgbtnSearch);
    }

}