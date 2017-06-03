package com.example.aum.smartyapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ForgotPwd extends AppCompatActivity {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private EditText email, newPass, confirmPass;
    private Button confirm;
    private int success = 0;
    private String str_mail = "", str_pwd = "", str_sem = "", str_confirm = "";
    private HTTPURLConnection service;
    private ProgressDialog dialog;
    private JSONObject jobj;
    private String path = "http://192.168.56.1/Android/forgot.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.accent_color));
        }
        setContentView(R.layout.activity_forgot_pwd);
        email = (EditText) findViewById(R.id.email);
        newPass = (EditText) findViewById(R.id.newPass);
        confirmPass = (EditText) findViewById(R.id.confirmPass);
        confirm = (Button) findViewById(R.id.button);
        service = new HTTPURLConnection();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_mail = email.getText().toString().trim();
                str_pwd = newPass.getText().toString().trim();
                str_confirm = confirmPass.getText().toString().trim();

                if (str_confirm.equals(str_pwd)) {
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (str_mail.matches(emailPattern)) {
                        //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                        new PostDataTOServer().execute();
                        //startActivity(new Intent(Registration.this, Login.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Password does not confirm", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    /*private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(ForgetPwdActivity.this);
        HttpURLConnection conn;
        URL url = null;


        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://172.16.1.24/vaccisafeapp/forgot.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("E_Mail", params[0])
                        .appendQueryParameter("Password", params[1]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading = new ProgressDialog(ForgetPwdActivity.this);
            pdLoading.setMessage("Please wait...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            if (pdLoading.isShowing())
                pdLoading.dismiss();
            if(success==1) {
                Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ForgetPwdActivity.this,ForgetPwdActivity.class));
            }else if(success == 0){
                Toast.makeText(getApplicationContext(),"Registraion successfully..!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(ForgetPwdActivity.this,Login.class));
            }

        }

        /*@Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();

            if(result.equalsIgnoreCase("true"))
            {
                Intent intent = new Intent(ForgetPwdActivity.this,MainActivity.class);
                startActivity(intent);
                ForgetPwdActivity.this.finish();

            }else if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
                Toast.makeText(ForgetPwdActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(ForgetPwdActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

            }
        }*/

   /* }*/


    private class PostDataTOServer extends AsyncTask<String, String, String> {

        String response = "";
        //Create hashmap Object to send parameters to web service
        HashMap<String, String> postDataParams;

        @Override
        protected String doInBackground(String... params) {

            postDataParams = new HashMap<String, String>();


            postDataParams.put("E_Mail", str_mail);
            postDataParams.put("Password", str_pwd);


            //Call ServerData() method to call webservice and store result in response
            response = service.ServerData(path, postDataParams);
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
            dialog = new ProgressDialog(ForgotPwd.this);
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
            dialog.show();
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            if (dialog.isShowing())
                dialog.dismiss();
            if (success == 1) {
                Toast.makeText(getApplicationContext(), "Email Doesn't Exists", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ForgotPwd.this, ForgotPwd.class));
            } else if (success == 0) {
                Toast.makeText(getApplicationContext(), "Password Updated..!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ForgotPwd.this, MainActivity.class));
            }

        }


    }
}
