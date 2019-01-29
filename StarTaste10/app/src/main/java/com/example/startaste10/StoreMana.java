package com.example.startaste10;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class StoreMana extends Fragment { // 마나 쪽은 수정 필요

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_mana, container, false);

        Context context = view.getContext();

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);


        ArrayList<StoreInfo> storeInfoArrayList = new ArrayList<>();
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "마나 1000", 0, "현금 1000원"));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "마나 2000", 0, "현금 2000원"));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "마나 3000", 0, "현금 3000원"));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "마나 4000", 0, "현금 4000원"));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "마나 5000", 0, "현금 5000원"));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "마나 6000", 0, "현금 6000원"));

        ReAdapterStore reAdapterStore = new ReAdapterStore(storeInfoArrayList);

        mRecyclerView.setAdapter(reAdapterStore);

        return view;
    }
}
