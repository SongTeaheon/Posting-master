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

import java.util.ArrayList;

public class StoreEnv extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.store_theme, container, false);

        Context context = view.getContext();

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);


        ArrayList<StoreInfo> storeInfoArrayList = new ArrayList<>();
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "흐르는 초코강", 1000, "레벨 10부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "피사의 젤리탑", 2000, "레벨 15부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "마카롱 언덕", 1000, "레벨 20부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "주렁주렁 사탕나무", 2000, "레벨 25부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "뭉개뭉개 솜사탕 구름", 1000, "레벨 30부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "매워매워 고춧가루 구름", 2000, "레벨 35부터 구매할 수 있습니다."));

        ReAdapterStore reAdapterStore = new ReAdapterStore(storeInfoArrayList);

        mRecyclerView.setAdapter(reAdapterStore);

        return view;
    }
}
