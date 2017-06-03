package com.example.aum.smartyapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Hp on 3/17/2016.
 */
public class MyHolderbus extends RecyclerView.ViewHolder implements View.OnClickListener {
    //OUR VIEWS

    TextView nameTxt, posTxt;
    ItemClickListener itemClickListener;

    public MyHolderbus(View itemView) {
        super(itemView);

        this.nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        this.posTxt = (TextView) itemView.findViewById(R.id.posTxt);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}