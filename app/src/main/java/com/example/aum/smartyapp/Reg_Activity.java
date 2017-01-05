package com.example.aum.smartyapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Reg_Activity extends AppCompatActivity  {
    String[] branch = {"AutoMobile", "Computer", "Civil", "EC", "Electrical", "IT", "Mechnical"};
    String[] sem = {"Semster-1", "Semster-2", "Semster-3", "Semster-4", "Semster-5", "Semster-6", "Semster-7", "Semster-8"};
    RadioGroup radio_g;
    RadioButton radio_b;
    private Button buttonRegister;
    final Context context = this;
    EditText enrollment, f_name, l_name, email, pass, confirm_pass, contact;
    AlertDialog.Builder builder;
    ImageView left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_dialog);
        enrollment = (EditText) findViewById(R.id.enrollment);
        f_name = (EditText) findViewById(R.id.first_name);
        l_name = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        confirm_pass = (EditText) findViewById(R.id.confirm_password);
        contact = (EditText) findViewById(R.id.contact);
        final Spinner brch = (Spinner) findViewById(R.id.branch);
        final Spinner sem1 = (Spinner) findViewById(R.id.sem);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, branch);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brch.setAdapter(aa);
        brch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //TODO Auto-generated method stub

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        ArrayAdapter<String> aa1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sem);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sem1.setAdapter(aa1);
        sem1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        radio_g = (RadioGroup) findViewById(R.id.radiog);
        int selected_id = radio_g.getCheckedRadioButtonId();
        radio_b = (RadioButton) findViewById(selected_id);
        // ImageView date=(ImageView)findViewById(R.id.imageView2);
        // final EditText usertext = (EditText)findViewById(R.id.dob);
        /** date.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {


        LayoutInflater layout = LayoutInflater.from(context);
        View custom = layout.inflate(R.layout.datepicker, null);
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
        alertdialog.setView(custom);
        final DatePicker date = (DatePicker)custom.findViewById(R.id.datePicker);
        alertdialog.setCancelable(false);
        alertdialog.setTitle("Set Date Of Birth");
        alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

        @Override public void onClick(DialogInterface dialog, int which) {
        usertext.setText(getCurrentDate());

        }
        private String getCurrentDate() {
        StringBuilder builder=new StringBuilder();

        builder.append((date.getMonth()+1)+"/");
        builder.append(date.getDayOfMonth()+"/");
        builder.append(date.getYear());
        return builder.toString();
        }
        });
        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

        @Override public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub

        //In this, dialog.cancel() method used to cancel the dialog box
        dialog.cancel();
        }
        });

        //at the end, we create dialog and run show() method
        AlertDialog ad = alertdialog.create();
        ad.show();


        }
        });*/
        left=(ImageView)findViewById(R.id.left) ;
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Reg_Activity.this,MainActivity.class);
                startActivity(i);
            }
        });

        buttonRegister = (Button) findViewById(R.id.sign);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Reg_Activity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}