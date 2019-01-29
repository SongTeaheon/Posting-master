package com.example.startaste;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class StoreFacility extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_facility, container, false);

        Context context = view.getContext();

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);


        ArrayList<StoreInfo> storeInfoArrayList = new ArrayList<>();
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "더 짠 소금광산", 1000, "레벨 10부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "더더 짠 소금광산", 2000, "레벨 20부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "더 달달한 사탕수수", 1000, "레벨 10부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "더더 달달한 사탕수수", 2000, "레벨 20부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "더 매운 후추나무", 1000, "레벨 10부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "더더 매운 후추나무", 2000, "레벨 20부터 구매할 수 있습니다."));

        ReAdapterStore reAdapterStore = new ReAdapterStore(storeInfoArrayList);

        mRecyclerView.setAdapter(reAdapterStore);

        return view;
    }
}
