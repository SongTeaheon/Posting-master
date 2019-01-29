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

public class StoreTheme extends Fragment {

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
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "핫뜨거 테마", 10000, "매운맛 속성 50부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "느끼느끼 치즈 테마", 20000, "지방맛 속성 50부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "달달한 달달 테마", 10000, "단맛 속성 50부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "소금으로 뒤덮인 테마", 20000, "짠맛 속성 50부터 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.zelda, "한식 매니아 테마", 10000, "한식 매니아 칭호 획득 시 구매할 수 있습니다."));
        storeInfoArrayList.add(new StoreInfo(R.mipmap.mapicon, "초록초록 알코올 테마", 20000, "알코올 매니아 칭호 획득 시 구매할 수 있습니다."));

        ReAdapterStore reAdapterStore = new ReAdapterStore(storeInfoArrayList);

        mRecyclerView.setAdapter(reAdapterStore);

        return view;
    }
}
