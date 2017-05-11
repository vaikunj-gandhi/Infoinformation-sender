package com.example.aum.smartyapp.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aum.smartyapp.R;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

/**
 * Created by aum on 10/29/2016.
 */

public class ScrollFragment3 extends Fragment {
    private ObservableScrollView mScrollView;
    TextView call,send,like,url,share,more;
    String call2,url2,share2;
    String[] send2={""};
    public static ScrollFragment3 newInstance() {
        return new ScrollFragment3();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scroll3, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);

        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);
        call=(TextView)view.findViewById(R.id.call);
        call2=call.getText().toString().trim();
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse(call2));

                startActivity(i);
            }
        });
        send=(TextView)view.findViewById(R.id.send);
      send2= new String[]{send.getText().toString().trim()};
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email=new Intent(Intent.ACTION_SEND);
               email.putExtra(Intent.EXTRA_EMAIL,send2);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,"send mail"));

            }
        });
        url=(TextView)view.findViewById(R.id.url);
        url2=url.getText().toString().trim();
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://in.linkedin.com/in/vaikunj-gandhi-668b58a6"));
                startActivity(browserIntent);
            }
        });
        share=(TextView)view.findViewById(R.id.share);
        share2=share.getText().toString().trim();
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });







    }
}
