package com.songtaeheon.posting.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.songtaeheon.posting.DataModel.NaverStoreInfo;
import com.songtaeheon.posting.R;

import java.util.ArrayList;

public class RecyclerviewAdapterForShare extends RecyclerView.Adapter<RecyclerviewAdapterForShare.ViewHolder> {

    private ArrayList<NaverStoreInfo> naverStoreInfoArray;
    private LayoutInflater mInflater;

    public RecyclerviewAdapterForShare( Context mContext, ArrayList<NaverStoreInfo> naverStoreInfoArray) {
        this.naverStoreInfoArray = naverStoreInfoArray;
        this.mInflater = LayoutInflater.from(mContext);;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }



    @Override
    public RecyclerviewAdapterForShare.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_store_search_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapterForShare.ViewHolder viewHolder, int i) {
        TextView tv_title;
        TextView tv_category;
        TextView tv_address;

        tv_title = viewHolder.view.findViewById(R.id.tv_title);
        tv_category = viewHolder.view.findViewById(R.id.tv_category);
        tv_address = viewHolder.view.findViewById(R.id.tv_address);

        tv_title.setText(naverStoreInfoArray.get(i).title);
        tv_category.setText(naverStoreInfoArray.get(i).category);
        tv_address.setText(naverStoreInfoArray.get(i).address);
    }

    @Override
    public int getItemCount() {
        return naverStoreInfoArray.size();
    }
}
