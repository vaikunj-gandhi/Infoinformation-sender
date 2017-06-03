package com.example.aum.smartyapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activities extends AppCompatActivity {
    String GET_JSON_DATA_HTTP_URL = "http://192.168.56.1/Android/displaynews.php";
    String JSON_SUB = "subject";
    String JSON_MSG = "notice_msg";
    String JSON_TIME = "created";
    String JSON_URl = "url";
    String JSON_ADDRESS = "sem";
    String JSON_DE = "name";
    View v;
    TextView t1, t2, t3;
    String sub, stream, msg, name, url, time;
    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;
    private List<NewsItem> listSuperHeroes;
    private SwipeRefreshLayout Swipe;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listSuperHeroes = new ArrayList<>();
        //Initializing Views
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(Activities.this);
        recyclerView.setLayoutManager(layoutManager);
        getData();
    }

    private void getData() {
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSON_PARSE_DATA_AFTER_WEBCALL(response);

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activities.this, "Server not Connected", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray response) {
        for (int i = 0; i < response.length(); i++) {
            NewsItem GetDataAdapter2 = new NewsItem();
            JSONObject json = null;
            try {
                json = response.getJSONObject(i);
                stream = json.getString(JSON_ADDRESS);
                sub = json.getString(JSON_SUB);
                msg = json.getString(JSON_MSG);
                name = json.getString(JSON_DE);
                url = json.getString(JSON_URl);
                time = json.getString(JSON_TIME);
                GetDataAdapter2.setName(json.getString(JSON_SUB));
                GetDataAdapter2.setAddress(json.getString(JSON_MSG));
                GetDataAdapter2.setTime(json.getString(JSON_TIME));
                GetDataAdapter2.setUrl(json.getString(JSON_URl));
                GetDataAdapter2.setFile(json.getString(JSON_DE));
                GetDataAdapter2.setStream(json.getString(JSON_ADDRESS));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listSuperHeroes.add(GetDataAdapter2);
        }
        adapter = new NewsAdapter(listSuperHeroes, Activities.this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListenerBus() {
            @Override
            public void onItemClick(NewsItem item) {
                Toast.makeText(Activities.this, item.getName(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(Activities.this, NEWS_HOME.class);
                Bundle bundle = new Bundle();
                bundle.putString("stream", item.getStream());
                bundle.putString("sub", item.getName());
                bundle.putString("msg", item.getAddress());
                bundle.putString("name", item.getFile());
                bundle.putString("url", item.geturl());
                bundle.putString("time", item.getTime());
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}
