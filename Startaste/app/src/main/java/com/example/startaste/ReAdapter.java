package com.example.startaste;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ReAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView styleName;
        TextView styleExplane;
        ProgressBar progressBar;
        TextView progress;

        MyViewHolder(View view){
            super(view);
            styleName = view.findViewById(R.id.styleName);
            styleExplane = view.findViewById(R.id.styleExplane);
            progressBar = view.findViewById(R.id.prograssBar);
            progress = view.findViewById(R.id.progress);
        }
    }

    private ArrayList<StyleInfo> styleInfoArrayList;
    ReAdapter(ArrayList<StyleInfo> styleInfoArrayList){
        this.styleInfoArrayList = styleInfoArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.styleName.setText(styleInfoArrayList.get(position).stylename);
        myViewHolder.styleExplane.setText(styleInfoArrayList.get(position).styleexplane);
        myViewHolder.progressBar.setProgress(styleInfoArrayList.get(position).progress);
        myViewHolder.progressBar.setMax(styleInfoArrayList.get(position).maxprogress);
        myViewHolder.progress.setText(styleInfoArrayList.get(position).progress + " / " +
                                        styleInfoArrayList.get(position).maxprogress);

    }

    @Override
    public int getItemCount() {
        return styleInfoArrayList.size();
    }
}
