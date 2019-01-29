package com.example.startaste10;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReAdapterStore extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView itemCost;
        TextView itemLevel;
        Button itembuy;

        MyViewHolder(View view){
            super(view);
            itemImage = view.findViewById(R.id.itemImage);
            itemName = view.findViewById(R.id.itemName);
            itemCost = view.findViewById(R.id.itemCost);
            itemLevel = view.findViewById(R.id.itemLevel);
            itembuy = view.findViewById(R.id.itemBuy);
        }
    }

    private ArrayList<StoreInfo> storeInfoArrayList;
    ReAdapterStore(ArrayList<StoreInfo> storeInfoArrayList){
        this.storeInfoArrayList = storeInfoArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row1, parent, false);

        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        final MyViewHolder myViewHolder = (MyViewHolder) holder;

        final int itemArray[] = new int[]{0, 0, 0, 0, 0, 0};
        myViewHolder.itembuy.setText("구매");

        myViewHolder.itemImage.setImageResource(storeInfoArrayList.get(position).itemImage);
        myViewHolder.itemName.setText(storeInfoArrayList.get(position).itemName);
        myViewHolder.itemCost.setText("필요 마나 : " + storeInfoArrayList.get(position).itemCost);
        myViewHolder.itemLevel.setText(storeInfoArrayList.get(position).itemLevel);
        myViewHolder.itembuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemArray[position] == 0) {
                    itemArray[position] = 1;
                    myViewHolder.itembuy.setText("구매완료");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeInfoArrayList.size();
    }
}
