package com.example.aum.smartyapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Findstudent extends AppCompatActivity implements View.OnClickListener {
    String enroll;
    CircleImageView imageView;
    private EditText editTextId;
    private Button buttonGet;
    private TextView textViewResult;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findstudent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editTextId = (EditText) findViewById(R.id.editTextId);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        imageView = (CircleImageView) findViewById(R.id.image);

        buttonGet.setOnClickListener(this);
    }


    private void getData() {
        String id = editTextId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

        enroll = editTextId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config2.DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                loading.dismiss();
                showJSON(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(MainActivity.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("enrollment", enroll);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String enrollment = "";
        String name = "";
        String email = "";
        String mobile = "";
        String semester = "";
        String branch = "";
        String img = "";
        String dob = "";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config2.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            enrollment = collegeData.getString(Config2.KEY_ENROLL);
            name = collegeData.getString(Config2.KEY_NAME);
            email = collegeData.getString(Config2.KEY_EMAIL);
            mobile = collegeData.getString(Config2.KEY_MOBILE);
            semester = collegeData.getString(Config2.KEY_SEM);
            branch = collegeData.getString(Config2.KEY_BRANCH);
            img = collegeData.getString(Config2.KEY_IMGURL);
            dob = collegeData.getString(Config2.JSON_DOB);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        textViewResult.setText("Name :\t" + name + "\nEmail :\t" + email + "\nBranch :\t" + branch + "\nSemester:\t" + semester + "\nMobile:\t" + mobile + "\nDOB:\t" + dob);
        Picasso.with(Findstudent.this).load(img)
                .placeholder(R.drawable.user).error(R.mipmap.ic_launcher)
                .into(imageView);

    }

    @Override
    public void onClick(View v) {

        getData();

    }
}