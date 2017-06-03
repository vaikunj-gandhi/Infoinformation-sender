package com.example.aum.smartyapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aum.smartyapp.model.UtilFunctions;

public class About extends AppCompatActivity {

    Animation anim1, anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ((TextView) findViewById(R.id.app_version_code)).setText(UtilFunctions.getVersion(About.this));

        findViewById(R.id.site).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://in.linkedin.com/in/vaikunj-gandhi-668b58a6"));
                startActivity(browserIntent);

            }
        });
        findViewById(R.id.mail_dev).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"vaikunj68@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello There");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Add Message here");

                emailIntent.setType("message/rfc822");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(About.this, "No email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
