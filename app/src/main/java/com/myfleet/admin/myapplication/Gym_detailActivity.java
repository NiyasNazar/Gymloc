package com.myfleet.admin.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Gym_detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_detail);
        String abc=getIntent().getStringExtra("gymname");
        Toast.makeText(getApplicationContext(),"da"+abc,Toast.LENGTH_LONG).show();
        Button b=(Button)findViewById(R.id.b1);
        b.setText(abc);
    }
}
