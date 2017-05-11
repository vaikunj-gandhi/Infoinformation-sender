package com.example.aum.smartyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Faculty_detailsHome extends AppCompatActivity {

    TextView t1,t2,t3,t4;
    String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CircleImageView imageView=(CircleImageView) findViewById(R.id.facultyImage) ;
        t1=(TextView)findViewById(R.id.Name);
        t2=(TextView)findViewById(R.id.Email);
        t3=(TextView)findViewById(R.id.Mobile);
        t4=(TextView)findViewById(R.id.Branch);
        Bundle bundle = getIntent().getExtras();
        t1.setText(bundle.getString("name"));
        t2.setText(bundle.getString("email"));
        t3.setText(bundle.getString("mobile"));
        t4.setText(bundle.getString("branch"));
        img=bundle.getString("img");
        Picasso.with(Faculty_detailsHome.this).load(img)
                .placeholder(R.drawable.hat).error(R.mipmap.ic_launcher)
                .into(imageView);

    }
}
