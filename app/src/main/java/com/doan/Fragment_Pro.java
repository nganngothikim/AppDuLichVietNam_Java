package com.doan;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_Pro extends Fragment {
    TextView txtlogout,tvUser;
    Fragment f;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__pro,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvUser =(TextView) view.findViewById(R.id.tvUser);
        txtlogout =(TextView) view.findViewById(R.id.txtlogout);
        tvUser.setText(User.userName);
        if(User.userName.isEmpty())
        {
            txtlogout.setText("Login");
        }
        txtlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(User.userName.isEmpty())
                {
                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);
                }
                else
                {
                    User.userName="";
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
      
    }

    private void loadFragment(Fragment k)
    {
        FragmentTransaction tra= getFragmentManager().beginTransaction();
        tra.replace(R.id.frame_container, k);
        tra.addToBackStack(null);
        tra.commit();
    }
}