package com.example.aum.smartyapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aum.smartyapp.R;
import com.example.aum.smartyapp.The_Campus;
import com.example.aum.smartyapp.Vision;

/**
 * Created by Hp on 3/15/2016.
 */
public class MyAdapter2 extends RecyclerView.Adapter<MyHolder> {

    //FIELDS TO STORE PASSED IN VALUES
    Context c;
    String[] players;
    String[] positions;
    int[] images;

    public MyAdapter2(Context ctx, String[] positions, int[] images) {
        //ASSIGN THEM
        this.c = ctx;

        this.positions = positions;
        this.images = images;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //INFLATE Institute VIEW FROM XML
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model2, null);

        //HOLDER
        MyHolder holder2 = new MyHolder(v);

        return holder2;
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
                        intent = new Intent(c, The_Campus.class);
                        break;

                    case 1:
                        intent = new Intent(c, Vision.class);
                        break;

                    default:
                        intent = new Intent(c, The_Campus.class);
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
