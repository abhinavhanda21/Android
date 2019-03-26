package com.example.apnamap;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class PersistanceActivity extends AppCompatActivity {

    EditText text_name,text_email,text_phone,text_pass;
    Button Register_btn;

    String data;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    void initViews(){
        text_name = findViewById(R.id.text_name);
        text_email = findViewById(R.id.text_email);
        text_phone = findViewById(R.id.text_phone);
        text_pass = findViewById(R.id.text_pass);

        Register_btn = findViewById(R.id.register_btn);


        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = text_name.getText().toString();
                data = text_email.getText().toString();
                data = text_phone.getText().toString();
                data = text_pass.getText().toString();

                saveDataInInternalFile();

                //readDataFromInternalFile();

                //saveDataInExternalFile();

                //editor.putString("keyQuote",data);
                //sharedPreferences.edit().apply(); // Save the Data in XML File | Background Thread
                //editor.commit();  // Save the Data in XML File

                //Toast.makeText(PersistanceActivity.this,"Data Saved in SharedPrefs",Toast.LENGTH_LONG).show();
                //eTxtData.setText("");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistance);

        initViews();
        //readDataFromInternalFile();
        //readDataFromExternalFile();

        data = sharedPreferences.getString("keyQuote","");
        text_name.setText(data);
        text_email.setText(data);
        text_phone.setText(data);
        text_pass.setText(data);


    }

    void saveDataInInternalFile(){
        try{

            FileOutputStream outputStream = openFileOutput("data.txt",MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();

        showAlertDialog();
            text_name.setText("");
            text_email.setText("");
            text_phone.setText("");
            text_pass.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You are registered");
        builder.setMessage("For any query click on help");
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Intent
                // or any action of your choice
                Intent intent = new Intent(PersistanceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void readDataFromInternalFile(){
        try{

            FileInputStream inputStream = openFileInput("data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            data = reader.readLine();
            text_name.setText(data);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void saveDataInExternalFile(){
        try{
            String path = Environment.getExternalStorageDirectory().getPath()+"/data.txt";
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(data.getBytes());
            outputStream.close();

            Toast.makeText(this,"Data Saved in External File",Toast.LENGTH_LONG).show();
            text_name.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readDataFromExternalFile(){
        try{
            String path = Environment.getExternalStorageDirectory().getPath()+"/data.txt";
            FileInputStream inputStream = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            data = reader.readLine();
            text_name.setText(data);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}