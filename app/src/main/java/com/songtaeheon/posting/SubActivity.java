package com.songtaeheon.posting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.songtaeheon.posting.DataModel.PostingInfo;
import com.songtaeheon.posting.DataModel.StoreInfo;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    private static final String TAG = "TAGSubActivity";
    RecyclerView my_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate is called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        my_recycler_view = (RecyclerView) findViewById(R.id.mrecyclerView);
        my_recycler_view.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);


    }



}
