package com.example.aum.smartyapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aum.smartyapp.R;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

/**
 * Created by aum on 10/29/2016.
 */

public class ScrollFragment extends Fragment {
    View v;


    String[] positions={"Institute","Intake", "Facilities" ,"News & Noties","Bus Routes"};
    int[] images={R.drawable.graduation,R.drawable.acadmic,R.drawable.inst,R.drawable.update,R.drawable.bus};

    private ObservableScrollView mScrollView;

    public static ScrollFragment newInstance() {
        return new ScrollFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_scroll, container, false);





        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
        //REFERENCE RECYCLER
        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);
        RecyclerView rv= (RecyclerView)view. findViewById(R.id.myRecycler);

        //SET PROPERTIES
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        MyAdapter adapter=new MyAdapter(getContext(),positions,images);
        rv.setAdapter(adapter);

    }
}
