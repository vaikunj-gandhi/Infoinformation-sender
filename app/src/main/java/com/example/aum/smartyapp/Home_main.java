package com.example.aum.smartyapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Home_main extends AppCompatActivity implements View.OnClickListener {
    private static Button downloadPdf, openDownloadedFolder;
    TextView t1, t2, t3, t4, t5, t6;
    String downloadDirectory = "Info. Sender";
    String downloadPdfUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        t1 = (TextView) findViewById(R.id.stream_h);
        t2 = (TextView) findViewById(R.id.sem_h);
        t3 = (TextView) findViewById(R.id.msg_h);
        t4 = (TextView) findViewById(R.id.name_h);
        t5 = (TextView) findViewById(R.id.fname_h);
        t6 = (TextView) findViewById(R.id.time);
        Bundle bundle = getIntent().getExtras();
        t1.setText(bundle.getString("Stream"));
        t2.setText(bundle.getString("sem"));
        t3.setText(bundle.getString("msg"));
        t4.setText(bundle.getString("name"));
        t5.setText(bundle.getString("fname"));
        t6.setText(bundle.getString("time"));
        downloadPdfUrl = bundle.getString("url");

        downloadPdf = (Button) findViewById(R.id.downloadPdf);

        openDownloadedFolder = (Button) findViewById(R.id.openfolder);
        // url="10.0.2.2:80/Android/vaikunj2(Resume)";
        downloadPdf.setOnClickListener(this);

        openDownloadedFolder.setOnClickListener(this);
        // img.setImageBitmap(bmp);

    }


    @Override
    public void onClick(View view) {
        //Before starting any download check internet connection availability
        switch (view.getId()) {
            case R.id.downloadPdf:
                if (isConnectingToInternet())
                    new DownloadTask(this, downloadPdf, downloadPdfUrl);
                else
                    Toast.makeText(this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.openfolder:
                openDownloadedFolder();
                break;

        }

    }

    //Open downloaded folder
    private void openDownloadedFolder() {
        //First check if SD Card is present or not
        if (new CheckForSDCard().isSDCardPresent()) {

            //Get Download Directory File
            File apkStorage = new File(
                    Environment.getExternalStorageDirectory() + "/"
                            + downloadDirectory);

            //If file is not present then display Toast
            if (!apkStorage.exists())
                Toast.makeText(this, "Right now there is no directory. Please download some file first.", Toast.LENGTH_SHORT).show();

            else {

                //If directory is present Open Folder

                /** Note: Directory will open only if there is a app to open directory like File Manager, etc.  **/

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/" + downloadDirectory);
                intent.setDataAndType(uri, "file/*");
                startActivity(Intent.createChooser(intent, "Open Download Folder"));
            }

        } else
            Toast.makeText(this, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

    }

    //Check if internet is present or not
    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}



