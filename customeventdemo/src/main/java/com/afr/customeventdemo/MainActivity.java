package com.afr.customeventdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomObject customObject1 = new CustomObject("Honorio");
        CustomObject customObject2 = new CustomObject("Carlota");
        CustomObject customObject3 = new CustomObject("Pepin");

        customObject1.setMyCustomObjectListener(new CustomObject.MyCustomObjectListener() {
            @Override
            public void onDataLoaded(String data) {
                Log.d("**",data);
            }
        });

        customObject2.setMyCustomObjectListener(new CustomObject.MyCustomObjectListener() {
            @Override
            public void onDataLoaded(String data) {
                Log.d("**",data);
            }
        });

        customObject3.setMyCustomObjectListener(new CustomObject.MyCustomObjectListener() {
            @Override
            public void onDataLoaded(String data) {
                Log.d("**",data);
            }
        });
    }
}
