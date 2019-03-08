package com.example.sub_ui_mk1;


import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Jeffrey Liu on 3/21/16.
 */
public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<PolarInfo> mList;
    private OnItemClickListener mItemClickListener;

    public HorizontalRecyclerAdapter() {
    }

    public void updateList(ArrayList<PolarInfo> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    private class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView imageView;
        private TextView mainView;

        public CellViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagefood);
            mainView = itemView.findViewById(R.id.textmain);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, mList.get(getLayoutPosition()).code);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemLongClick(view, mList.get(getLayoutPosition()).code);
                return true;
            }
            return false;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            default: {
                View v1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_list_item_type_title, viewGroup, false);
                return new CellViewHolder(v1);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            default: {
                CellViewHolder cellViewHolder = (CellViewHolder) viewHolder;
                cellViewHolder.imageView.setImageResource(mList.get(position).imagefood);
                cellViewHolder.mainView.setText(""+mList.get(position).main);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int code);

        void onItemLongClick(View view, int code);
    }

    // for both short and long click
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}