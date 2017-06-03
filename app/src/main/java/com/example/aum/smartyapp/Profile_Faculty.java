package com.example.aum.smartyapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by aum on 5/9/2017.
 */

public class Profile_Faculty extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5;
    String img_url, fmobile, fgender, fbranch;
    CircleImageView img;
    ImageView imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_faculty);
        t1 = (TextView) findViewById(R.id.user_profile_name);
        t2 = (TextView) findViewById(R.id.user_profile_email);
        t3 = (TextView) findViewById(R.id.faculty_mobile);
        t4 = (TextView) findViewById(R.id.logoyt_faculty);
        t5 = (TextView) findViewById(R.id.faculty_branch);

        SharedPreferences sharedPreferences = Profile_Faculty.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        t2.setText(sharedPreferences.getString("email", "Not Available"));
        t1.setText(sharedPreferences.getString("name", "Not Available"));
        img_url = sharedPreferences.getString("image", "Not Available");
        fmobile = sharedPreferences.getString("mobile", "Not Available");
        fgender = sharedPreferences.getString("gender", "Not Available");
        fbranch = sharedPreferences.getString("branch", "Not Available");
        t3.setText(fmobile);

        t5.setText(fbranch);

        img = (CircleImageView) findViewById(R.id.facultyImage_profile);
        Picasso.with(Profile_Faculty.this).load(img_url)
                .placeholder(R.drawable.hat).error(R.mipmap.ic_launcher)
                .into(img);
        imgback = (ImageView) findViewById(R.id.back_home);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile_Faculty.this, AppBase.class);
                startActivity(i);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


    }

    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(Profile_Faculty.this, MainActivity.class);
                        startActivity(intent);


                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
