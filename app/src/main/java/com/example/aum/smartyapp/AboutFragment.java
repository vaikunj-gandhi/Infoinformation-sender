package com.example.aum.smartyapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by aum on 8/13/2016.
 */
public class AboutFragment extends Fragment {
    View v;
    TextView t1,t2;
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    private String str_mail="",str_sem="",str_enrol="",str_name="",str_mobile="",str_branch="";
    private Button confirm;
    private HTTPURLConnection service;
    private ProgressDialog dialog;
    private JSONObject jobj;
    private int success =0;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private String path = "http://192.168.56.1/Android/update_profile.php";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v=inflater.inflate(R.layout.aboutus,container,false);

        // t1=(TextView)v.findViewById(R.id.sem4);
        e1=(EditText)v.findViewById(R.id.enrollment_a);
        e2=(EditText)v.findViewById(R.id.name_a);
        e3=(EditText)v.findViewById(R.id.email_a);
        e4=(EditText)v.findViewById(R.id.mobile_a);
        e5=(EditText)v.findViewById(R.id.sem_a);
        e6=(EditText)v.findViewById(R.id.branch_a);
        confirm = (Button)v.findViewById(R.id.update_a);
        service = new HTTPURLConnection();



        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //t1.setText( sharedPreferences.getString("branch","Not Available"));
        e1.setText( sharedPreferences.getString("enrollment","Not Available"));
        e2.setText( sharedPreferences.getString("name","Not Available"));
        e3.setText( sharedPreferences.getString("email","Not Available"));
        e4.setText( sharedPreferences.getString("mobile","Not Available"));
        e5.setText( sharedPreferences.getString("semester","Not Available"));
        e6.setText( sharedPreferences.getString("branch","Not Available"));
        e3.setKeyListener(null);
        e3.setEnabled(false);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_mail = e3.getText().toString().trim();
                str_sem = e5.getText().toString().trim();
                str_enrol=e1.getText().toString().trim();
                str_name=e2.getText().toString().trim();
                str_mobile=e4.getText().toString().trim();
                str_branch=e6.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (str_mail.matches(emailPattern))
                    {
                        //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                        new PostDataTOServer().execute();
                        //startActivity(new Intent(Registration.this, Login.class));
                    }

                    else
                    {
                        Toast.makeText(getActivity(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }


        });

       // t1.setText( sharedPreferences.getString("branch","Not Available"));
        return v;
    }

    private class PostDataTOServer extends AsyncTask<String, String, String> {

        String response = "";
        //Create hashmap Object to send parameters to web service
        HashMap<String, String> postDataParams;

        @Override
        protected String doInBackground(String... params) {

            postDataParams=new HashMap<String, String>();


            postDataParams.put("E_Mail", str_mail);
            postDataParams.put("sem",str_sem);
            postDataParams.put("name",str_name);
            postDataParams.put("enroll",str_enrol);
            postDataParams.put("branch",str_branch);
            postDataParams.put("mobile",str_mobile);


            //Call ServerData() method to call webservice and store result in response
            response= service.ServerData(path,postDataParams);
            try {
                jobj = new JSONObject(response);
                //Get Values from JSONobject
                System.out.println("success=" + jobj.get("success"));
                success = jobj.getInt("success");


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getContext());
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
            dialog.show();
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            if (dialog.isShowing())
                dialog.dismiss();
            if(success==1) {
                Toast.makeText(getActivity(), "Email Doesn't Exists",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(),getActivity().getClass()));
            }else if(success == 0){
                Toast.makeText(getActivity(),"Password Updated..!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(),getActivity().getClass()));
            }

        }


    }
}
