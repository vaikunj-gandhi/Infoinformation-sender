package com.example.aum.smartyapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

public class AppBase extends AppCompatActivity {

    public static ArrayList<String> divisions;
    public static databaseHandler handler;
    public static Activity activity;
    ArrayList<String> basicFields;
    gridAdapter adapter;
    GridView gridView;
    String t1, t2, img_url, fmobile, fgender, fbranch;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mai_menu, menu);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        basicFields = new ArrayList<>();
        handler = new databaseHandler(this);
        activity = this;
        SharedPreferences sharedPreferences = AppBase.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        t2 = sharedPreferences.getString("email", "Not Available");
        t1 = sharedPreferences.getString("name", "Not Available");
        img_url = sharedPreferences.getString("image", "Not Available");
        fmobile = sharedPreferences.getString("mobile", "Not Available");
        fgender = sharedPreferences.getString("gender", "Not Available");
        fbranch = sharedPreferences.getString("branch", "Not Available");
        getSupportActionBar().show();
        divisions = new ArrayList();
        divisions.add("SEMESTER 1");
        divisions.add("SEMESTER 2");
        divisions.add("SEMESTER 3");
        divisions.add("SEMESTER 4");
        divisions.add("SEMESTER 5");
        divisions.add("SEMESTER 6");
        divisions.add("SEMESTER 7");
        divisions.add("SEMESTER 8");
        gridView = (GridView) findViewById(R.id.grid);
        basicFields.add("ATTENDANCE");
        basicFields.add("SCHEDULER");
        basicFields.add("NOTES");
        basicFields.add("ATTENDANCE CALCULATION");
        basicFields.add("Student Detail");
        basicFields.add("Your Profile");
        adapter = new gridAdapter(this, basicFields);
        gridView.setAdapter(adapter);
    }

    public void loadSettings(MenuItem item) {
        Intent launchIntent = new Intent(this, SettingsActivity.class);
        startActivity(launchIntent);
    }

    public void loadAbout(MenuItem item) {
        Intent launchIntent = new Intent(this, About.class);
        startActivity(launchIntent);
    }

    public void loadLogout(MenuItem item) {
        logout();
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
                        Intent intent = new Intent(AppBase.this, MainActivity.class);
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
