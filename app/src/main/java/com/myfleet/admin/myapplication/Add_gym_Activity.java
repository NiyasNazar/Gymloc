package com.myfleet.admin.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Add_gym_Activity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gym_);
        ed1=(EditText)findViewById(R.id.ed_gymname);
        ed2=(EditText)findViewById(R.id.ed_location);
        ed3=(EditText)findViewById(R.id.ed_monthlyfees);
        ed4=(EditText)findViewById(R.id.ed_regfees);
        db = FirebaseFirestore.getInstance();
    }

    public void Add_gym(View view) {
        CollectionReference cities = db.collection("GYM");
        String gymname=ed1.getText().toString();
        String gymloc=ed2.getText().toString();
        String gymmonthfees=ed3.getText().toString();
        String gymregfees=ed4.getText().toString();


        Map<String, Object> data1 = new HashMap<>();
        data1.put("gymname", gymname);
        data1.put("gymloc", gymloc);
        data1.put("gymmonthfees", gymmonthfees);
        data1.put("capital", false);
        data1.put("gymregfees", gymregfees);

        cities.document(gymname).set(data1);



    }
}
