package com.songtaeheon.posting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.songtaeheon.posting.DataModel.PostingInfo;
import com.songtaeheon.posting.DataModel.StoreInfo;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    ArrayList<StoreInfo> allSampleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        allSampleData = new ArrayList<StoreInfo>();

        createDummyData();

        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.mrecyclerView);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);

    }

    public void createDummyData() {// test를 위한 Sample Data 구성 차후 firestore에서 받은 자료로 구성해야함
        for (int i = 1; i <= 5; i++) {

            StoreInfo dm = new StoreInfo();

            dm.setName("Section " + i);

            ArrayList<PostingInfo> singleItem = new ArrayList<PostingInfo>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new PostingInfo("달방"+i+j, "", 1, "", "", null, "", "", "", "", "", (float) 0, null));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
    }
}
