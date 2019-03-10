package com.songtaeheon.posting;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.songtaeheon.posting.DataModel.StoreInfo;

import java.util.ArrayList;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder> { // 세로 리사이클러 뷰를 위한 어뎁터

    private ArrayList<StoreInfo> list;
    private Context mContext;

    public RecyclerViewDataAdapter(Context context, ArrayList<StoreInfo> mlist) {
        this.list = mlist;
        this.mContext = context;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vertical_items, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        final String sectionName = list.get(i).getName();

        ArrayList singleSectionItems = list.get(i).getAllItemsInSection();

        itemRowHolder.storeName.setText(sectionName);

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems);

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);


        itemRowHolder.touchStore.setOnClickListener(new View.OnClickListener() { // 각 가게별 상단 바를 터치 했을 때 이벤트 설정
            @Override
            public void onClick(View v) {


                Toast.makeText(v.getContext(), "click event on more, "+sectionName , Toast.LENGTH_SHORT).show();



            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        public CardView touchStore;
        public TextView storeName;
        public TextView storeStar;
        public RecyclerView recycler_view_list;



        public ItemRowHolder(View view) {
            super(view);

            this.touchStore = (CardView) view.findViewById(R.id.touchStore);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recyclerView);
            storeName = (TextView) view.findViewById(R.id.storeName);
            storeStar = (TextView) view.findViewById(R.id.storeStar);


        }

    }

}