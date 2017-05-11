package com.example.aum.smartyapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aum on 8/13/2016.
 */
public class MidsemFragment extends Fragment {
    String GET_JSON_DATA_HTTP_URL = "http://192.168.56.1/Android/result2.php";
    String JSON_NAME = "stream";
    String JSON_ADDRESS = "sem";
    String JSON_MSG = "msg";
    String JSON_DE = "name";
    String JSON_FNAME = "f_name";
    String JSON_URl = "url";
    String JSON_TIME = "created";
    private List<DataItem> messages;
    View v;


   // private ActionModeCallback actionModeCallback;

    private ActionMode actionMode;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter mAdapter;
    TextView t1,t2,t3;
    String sem1,stream,s_sem, s_branch,msg,name,fname,url;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.note,container,false);
        messages = new ArrayList<>();
        //Initializing Views
        recyclerView = (RecyclerView)v. findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

       // actionModeCallback = new ActionModeCallback();

        // /Initializing our superheroes list

        t1=(TextView)v.findViewById(R.id.sem);
        t2=(TextView)v.findViewById(R.id.branch);
        t3=(TextView)v.findViewById(R.id.time);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        t2.setText( sharedPreferences.getString("semester","Not Available"));
        t1.setText( sharedPreferences.getString("branch","Not Available"));
        s_sem = t2.getText().toString().trim();
        s_branch=t1.getText().toString().trim();
        //Calling method to get data
        getData();
return v;
    }

    //This method will get data from the web api
    private void getData(){
        //Showing a progress dialog

        StringRequest stringRequest=new StringRequest(Request.Method.POST,GET_JSON_DATA_HTTP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseData(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG ).show();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("stream",s_branch);
                params.put("sem",s_sem);

                return params;
            }
        };
        MySingleton.getInstance(getContext()).addRequestQueue(stringRequest);
    }

    //This method will parse json data
    private void parseData(String array){
        for(int i = 0; i<array.length(); i++) {
            DataItem superHero = new DataItem();
            // JSONObject json =null;
            try {
                JSONArray jsonArray = new JSONArray(array);
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //JSONObject jsonObject=jsonArray.getJSONObject();
                stream = jsonObject.getString(JSON_NAME);
                sem1 = jsonObject.getString(JSON_ADDRESS);
                msg=jsonObject.getString(JSON_MSG);
                name=jsonObject.getString(JSON_DE);
                fname=jsonObject.getString(JSON_FNAME);
                url=jsonObject.getString(JSON_URl);
                if (s_branch.equals(stream) && s_sem.equals(sem1)) {
                    superHero.setName(jsonObject.getString(JSON_NAME));
                    superHero.setAddress(jsonObject.getString(JSON_ADDRESS));
                    superHero.setMsg(jsonObject.getString(JSON_MSG));
                    superHero.setFile(jsonObject.getString(JSON_DE));
                    superHero.setFname(jsonObject.getString(JSON_FNAME));
                    superHero.setUrl(jsonObject.getString(JSON_URl));

                } else {


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            messages.add(superHero);
        }
        mAdapter=new RecyclerAdapter(messages,getContext());
        recyclerView.setAdapter(mAdapter);
       /** adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
           /** public void onItemClick(DataItem item) {
                Toast.makeText(MainActivity.this,item.getName(),Toast.LENGTH_LONG).show();
                Intent i=new Intent(MainActivity.this,Home.class);
                Bundle bundle=new Bundle();
                bundle.putString("Stream",item.getName());
                bundle.putString("sem",item.getAddress());
                bundle.putString("msg",item.getMsg());
                bundle.putString("name",item.getFile());
                bundle.putString("fname",item.getFname());
                bundle.putString("url",item.getUrl());
                i.putExtras(bundle);
                startActivity(i);
            }
        });*/
    }
}