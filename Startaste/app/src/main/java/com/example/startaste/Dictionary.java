package com.example.startaste;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class Dictionary extends AppCompatActivity {

    DicPage1 dicPage1;
    DicPage2 dicPage2;
    FloatingActionButton floatingActionButton;
    FloatingActionButton floatingActionButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container1, new DicPage1());
        fragmentTransaction.commit();

        dicPage1 = new DicPage1();
        dicPage2 = new DicPage2();

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container1, dicPage2).commit();
                floatingActionButton.setVisibility(View.INVISIBLE);
                floatingActionButton1.setVisibility(View.VISIBLE);
            }
        });

        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.floatingActionButton1);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container1, dicPage1).commit();
                floatingActionButton.setVisibility(View.VISIBLE);
                floatingActionButton1.setVisibility(View.INVISIBLE);
            }
        });

    }
}
