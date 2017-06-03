package com.example.aum.smartyapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aum.smartyapp.Academics;
import com.example.aum.smartyapp.Activities;
import com.example.aum.smartyapp.BusRoute;
import com.example.aum.smartyapp.Facilities;
import com.example.aum.smartyapp.Institute;
import com.example.aum.smartyapp.R;

/**
 * Created by Hp on 3/15/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    //FIELDS TO STORE PASSED IN VALUES
    Context c;
    String[] players;
    String[] positions;
    int[] images;

    public MyAdapter(Context ctx, String[] positions, int[] images) {
        //ASSIGN THEM
        this.c = ctx;

        this.positions = positions;
        this.images = images;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //INFLATE Institute VIEW FROM XML
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, null);

        //HOLDER
        MyHolder holder = new MyHolder(v);

        return holder;
    }


    //DATA IS BEING BOUND TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        //BIND DATA

        holder.posTxt.setText(positions[position]);
        holder.img.setImageResource(images[position]);

        //WHEN ITEM IS CLICKED
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                //INTENT OBJ
                final Intent intent;
                switch (pos) {
                    case 0:
                        intent = new Intent(c, Institute.class);
                        break;

                    case 1:
                        intent = new Intent(c, Academics.class);
                        break;
                    case 2:
                        intent = new Intent(c, Facilities.class);
                        break;

                    case 3:
                        intent = new Intent(c, Activities.class);
                        break;
                    case 4:
                        intent = new Intent(c, BusRoute.class);
                        break;

                    default:
                        intent = new Intent(c, Institute.class);
                        break;
                }
                c.startActivity(intent);

            }
        });

    }

    //TOTAL PLAYERS
    @Override
    public int getItemCount() {
        return positions.length;
    }
}
