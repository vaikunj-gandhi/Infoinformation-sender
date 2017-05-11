package com.example.aum.smartyapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

import static android.content.Context.MODE_PRIVATE;

public class LoginActivity extends Fragment {
    ImageView close;
    Button signup,login;
    private EditText e1,e2;
    public static final String SHARED_PREF_NAME = "myloginapp";

    private boolean loggedIn = false;
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    String username,password;
    String login_url="http://192.168.56.1/Android/login.php";
    android.support.v7.app.AlertDialog.Builder builder;
    View v;
    private AlertDialog alert;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_login, container, false);

        e1=(EditText)v.findViewById(R.id.enrollment);
        e2=(EditText)v.findViewById(R.id.password);
        close = (ImageView) v.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        TextView t1=(TextView)v.findViewById(R.id.forgotpwd);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ForgotPwd.class);
                startActivity(intent);
            }
        });
        TextView t2=(TextView)v.findViewById(R.id.faculty);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Faculty_login Fragment=new Faculty_login();
                android.support.v4.app.FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content,Fragment);
                fragmentTransaction.commit();

            }
        });


        builder=new android.support.v7.app.AlertDialog.Builder(getContext());
        login = (Button) v.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                username = e1.getText().toString().trim();
                password = e2.getText().toString().trim();
                if(username.equals("") || password.equals("")){
                    builder.setTitle("Some went wrong");
                    displayAlert("Enter valid info");

                }else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                if(code.equals("login_false")){
                                    builder.setTitle("Login error");
                                    displayAlert("Enter valid info");
                                } else {
                                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                    //Creating editor to store values to shared preferences
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    //Adding values to editor
                                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                    editor.putString(Config.EMAIL_SHARED_PREF, username);
                                    editor.putString("enrollment", jsonObject.getString("enrollment"));
                                    editor.putString("name", jsonObject.getString("name"));
                                    editor.putString("mobile", jsonObject.getString("mobile"));
                                    editor.putString("gender", jsonObject.getString("gender"));
                                    editor.putString("dob", jsonObject.getString("dob"));
                                    editor.putString("img_url",jsonObject.getString("img_url"));
                                    editor.putString("semester",jsonObject.getString("semester"));
                                    editor.putString("branch",jsonObject.getString("branch"));
                                    //Saving values to editor
                                    editor.commit();
                                    Intent i = new Intent(getActivity(), Main2Activity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("name", jsonObject.getString("name"));
                                    bundle.putString("email", jsonObject.getString("email"));
                                    bundle.putString("semester",jsonObject.getString("semester"));
                                    bundle.putString("branch",jsonObject.getString("branch"));
                                    bundle.putString("img_url",jsonObject.getString("img_url"));
                                    // bundle.putString("image",jsonObject.getString("image"));
                                    i.putExtras(bundle);


                                    startActivity(i);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Snackbar.make(v, "Server Not Connected", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
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
        android.support.v7.app.AlertDialog alertDialog=builder.create();
        alertDialog.show();



    }
    @Override
    public void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(getContext(),Main2Activity.class);
            startActivity(intent);
        }
    }

}
