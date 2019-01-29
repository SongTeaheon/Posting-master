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

public class ProfileStyle extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_style, container, false);

        Context context = view.getContext();

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<StyleInfo> styleInfoArrayList = new ArrayList<>();
        styleInfoArrayList.add(new StyleInfo("와카레마시따", "카레를 10번 먹어 달성", 10, 10));
        styleInfoArrayList.add(new StyleInfo("지옥의 불맛", "매운맛 속성을 30올려 달성", 30, 15));
        styleInfoArrayList.add(new StyleInfo("꿈처럼 달콤한", "단맛 속성을 30올려 달성", 30, 20));
        styleInfoArrayList.add(new StyleInfo("와카레마시따", "카레를 10번 먹어 달성", 10, 10));
        styleInfoArrayList.add(new StyleInfo("지옥의 불맛", "매운맛 속성을 30올려 달성", 30, 15));
        styleInfoArrayList.add(new StyleInfo("꿈처럼 달콤한", "단맛 속성을 30올려 달성", 30, 20));
        styleInfoArrayList.add(new StyleInfo("와카레마시따", "카레를 10번 먹어 달성", 10, 10));
        styleInfoArrayList.add(new StyleInfo("지옥의 불맛", "매운맛 속성을 30올려 달성", 30, 15));
        styleInfoArrayList.add(new StyleInfo("꿈처럼 달콤한", "단맛 속성을 30올려 달성", 30, 20));
        styleInfoArrayList.add(new StyleInfo("와카레마시따", "카레를 10번 먹어 달성", 10, 10));
        styleInfoArrayList.add(new StyleInfo("지옥의 불맛", "매운맛 속성을 30올려 달성", 30, 15));
        styleInfoArrayList.add(new StyleInfo("꿈처럼 달콤한", "단맛 속성을 30올려 달성", 30, 20));
        styleInfoArrayList.add(new StyleInfo("와카레마시따", "카레를 10번 먹어 달성", 10, 10));
        styleInfoArrayList.add(new StyleInfo("지옥의 불맛", "매운맛 속성을 30올려 달성", 30, 15));
        styleInfoArrayList.add(new StyleInfo("꿈처럼 달콤한", "단맛 속성을 30올려 달성", 30, 20));

        ReAdapter reAdapter = new ReAdapter(styleInfoArrayList);

        mRecyclerView.setAdapter(reAdapter);

        return view;
    }
}
