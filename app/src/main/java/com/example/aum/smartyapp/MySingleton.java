package com.example.aum.smartyapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by aum on 12/16/2016.
 */

public class MySingleton {
    private static MySingleton mySingleton;
    private static Context context;
    private RequestQueue requestQueue;

    private MySingleton(Context context1) {
        context = context1;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context3)

    {
        if (mySingleton == null) {
            mySingleton = new MySingleton(context3);


        }
        return mySingleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }

    public <T> void addRequestQueue(Request<T> request) {
        requestQueue.add(request);
    }
}
