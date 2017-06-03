package com.example.aum.smartyapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Student on 1/13/2017.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private List<DataItem> itemList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public RecyclerAdapter(List<DataItem> itemList, Context context) {
        super();
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.datapage, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, itemList, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DataItem getDataAdapter1 = itemList.get(position);
        holder.name.setText(String.valueOf(getDataAdapter1.getName()));
        holder.address.setText(String.valueOf(getDataAdapter1.getAddress()));
        holder.msg.setText(String.valueOf(getDataAdapter1.getMsg()));
        holder.t3.setText(String.valueOf(getDataAdapter1.getTime()));
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onItemClickListener.onItemClick(getDataAdapter1);


            }
        };
        holder.name.setOnClickListener(listener);
        holder.address.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView address;
        public TextView msg, t3;
        List<DataItem> itemList;
        Context ctx2;

        public ViewHolder(View itemView, List<DataItem> itemList, Context ctx2) {
            super(itemView);
            this.ctx2 = ctx2;
            this.itemList = itemList;
            this.name = (TextView) itemView.findViewById(R.id.textstream);
            this.address = (TextView) itemView.findViewById(R.id.textsem);
            this.msg = (TextView) itemView.findViewById(R.id.textmsg);
            this.t3 = (TextView) itemView.findViewById(R.id.time);

        }


    }
}
