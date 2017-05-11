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

public class ScrollFragment2 extends Fragment {
    String[] positions={"The Campus","Vision & Mission" };
    int[] images={R.drawable.campus,R.drawable.vision,R.drawable.admin_home};
    private ObservableScrollView mScrollView;
    public static ScrollFragment2 newInstance() {
        return new ScrollFragment2();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scroll2, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);

        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);
        RecyclerView rv= (RecyclerView)view. findViewById(R.id.myRecycler);

        //SET PROPERTIES
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        MyAdapter2 adapter=new MyAdapter2(getContext(),positions,images);
        rv.setAdapter(adapter);
    }
}
