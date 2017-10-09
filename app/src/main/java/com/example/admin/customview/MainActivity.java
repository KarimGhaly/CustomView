package com.example.admin.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomView customView = new CustomView(this);
        TextViewCustomView textViewCustomView = new TextViewCustomView(this);
        CustomLayout customLayout = new CustomLayout(this);
    }
}
