package com.example.aum.smartyapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aum on 5/4/2017.
 */

public class Faculty_login extends Fragment {
    View v;
    String username, password, semester, branch;
    String login_url = "http://192.168.56.1/Android/facultylogin.php";
    AlertDialog.Builder builder;
    TextView forget;
    ImageView close, left;
    private EditText e1, e2;
    private AppCompatButton b1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.faculty_login, container, false);
        e1 = (EditText) v.findViewById(R.id.faculty_email);
        e2 = (EditText) v.findViewById(R.id.faculty_password);
        forget = (TextView) v.findViewById(R.id.faculty_forgotpwd);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent i=new Intent(LoginActivity.this,ForgetPwdActivity.class);
                //startActivity(i);
            }
        });
        close = (ImageView) v.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        left = (ImageView) v.findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity Fragment = new LoginActivity();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, Fragment);
                fragmentTransaction.commit();
            }
        });

        b1 = (AppCompatButton) v.findViewById(R.id.faculty_login);

        builder = new AlertDialog.Builder(getActivity());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = e1.getText().toString().trim();
                password = e2.getText().toString().trim();
                if (username.equals("") || password.equals("")) {
                    builder.setTitle("Some went wrong");
                    displayAlert("Enter valid info");

                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                if (code.equals("login_false")) {
                                    builder.setTitle("Login error");
                                    displayAlert("Enter valid info");
                                } else {
                                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                    //Creating editor to store values to shared preferences
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    //Adding values to editor
                                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                    editor.putString("name", jsonObject.getString("name"));
                                    editor.putString("mobile", jsonObject.getString("mobile"));
                                    editor.putString("gender", jsonObject.getString("gender"));
                                    editor.putString("email", jsonObject.getString("email"));
                                    editor.putString("image", jsonObject.getString("image"));
                                    editor.putString("branch", jsonObject.getString("branch"));
                                    //Saving values to editor
                                    editor.commit();
                                    Intent i = new Intent(getActivity(), AppBase.class);


                                    startActivity(i);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", username);
                            params.put("password", password);

                            return params;
                        }
                    };
                    MySingleton.getInstance(getContext()).addRequestQueue(stringRequest);
                }
            }
        });
        return v;
    }

    public void displayAlert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                e1.setText("");
                e2.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
