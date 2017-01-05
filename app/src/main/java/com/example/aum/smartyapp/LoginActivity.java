package com.example.aum.smartyapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class LoginActivity extends Fragment{
    ImageView close;
    Button signup,login;

    View v;
    private AlertDialog alert;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_login, container, false);

        close=(ImageView)v.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),MainActivity.class);
                startActivity(i);
            }
        });
        signup=(Button)v.findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getContext(),Reg_Activity.class);
                startActivity(intent);
            }
        });

        login=(Button)v.findViewById(R.id.login);
       login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getContext(),Main2Activity.class);
               startActivity(intent);
            }
       });
        return v;



    }
}
