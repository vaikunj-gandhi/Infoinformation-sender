package com.example.aum.smartyapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.util.ArrayList;

public class Academics extends ActionBarActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gridView = (GridView) findViewById(R.id.galleryGridView);
        gridAdapter = new GridViewAdapter(this,R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                final Intent intent;
                switch (position){
                    case 0:
                        intent =  new Intent(Academics.this, Course.class);
                        break;

                    case 1:
                        intent =  new Intent(Academics.this, Elaectrical.class);
                        break;
                    case 2:
                        intent =  new Intent(Academics.this, Civil.class);
                        break;

                    case 3:
                        intent =  new Intent(Academics.this, Ec.class);
                        break;
                    case 4:
                        intent =  new Intent(Academics.this,Auto.class);
                        break;
                    case 5:
                        intent =  new Intent(Academics.this,Comp.class);
                        break;
                    case 6:
                        intent =  new Intent(Academics.this,IT.class);
                        break;

                    default:
                        intent =  new Intent(Academics.this, Elaectrical.class);
                        break;
                }


                //Start details activity
                startActivity(intent);
            }
        });
    }

    /**
     * Prepare some dummy data for gridview
     */
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.mech);
            imageItems.add(new ImageItem(bitmap, "Mechnical Engineering"));
            Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.drawable.electrical);
            imageItems.add(new ImageItem(bitmap2, "Electrical Engineering"));
            Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(),R.drawable.civil);
            imageItems.add(new ImageItem(bitmap3, "Civil Engineering"));
            Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(),R.drawable.ec);
            imageItems.add(new ImageItem(bitmap4, "Electronics & Communication"));
            Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(),R.drawable.auto);
            imageItems.add(new ImageItem(bitmap5, "Automobile Engineering"));
            Bitmap bitmap6 = BitmapFactory.decodeResource(getResources(),R.drawable.comp);
            imageItems.add(new ImageItem(bitmap6, "Computer Engineering"));
            Bitmap bitmap7 = BitmapFactory.decodeResource(getResources(),R.drawable.it);
            imageItems.add(new ImageItem(bitmap7, "Information Technology"));


        return imageItems;
    }
}
