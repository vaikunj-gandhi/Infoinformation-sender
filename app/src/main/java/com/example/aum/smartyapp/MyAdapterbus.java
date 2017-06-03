package com.example.aum.smartyapp;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

/**
 * Created by Hp on 3/17/2016.
 */
public class MyAdapterbus extends RecyclerView.Adapter<MyHolderbus> implements Filterable {
    Context c;
    ArrayList<Player> players, filterList;
    CustomFilter filter;

    public MyAdapterbus(Context ctx, ArrayList<Player> players) {
        this.c = ctx;
        this.players = players;
        this.filterList = players;
    }

    @Override
    public MyHolderbus onCreateViewHolder(ViewGroup parent, int viewType) {
        //CONVERT XML TO VIEW ONBJ
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        //HOLDER
        MyHolderbus holder = new MyHolderbus(v);
        return holder;
    }

    //DATA BOUND TO VIEWS
    @Override
    public void onBindViewHolder(MyHolderbus holder, int position) {
        //BIND DATA
        holder.posTxt.setText(players.get(position).getPos());
        holder.nameTxt.setText(players.get(position).getName());

        //IMPLEMENT CLICK LISTENET
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Snackbar.make(v, players.get(pos).getName(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    //GET TOTAL NUM OF PLAYERS
    @Override
    public int getItemCount() {
        return players.size();
    }

    //RETURN FILTER OBJ
    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(filterList, this);
        }
        return filter;
    }
}