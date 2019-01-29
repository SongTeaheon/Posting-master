package com.example.startaste10;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DicPage2 extends Fragment {

    ImageButton monster4;
    ImageButton monster5;
    ImageButton monster6;

    @Override
    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.dic_page2, null);

        monster4 = (ImageButton) view.findViewById(R.id.monster4);
        monster5 = (ImageButton) view.findViewById(R.id.monster5);
        monster6 = (ImageButton) view.findViewById(R.id.monster6);

        monster4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dic_dialog);

                TextView tv1 = (TextView) dialog.findViewById(R.id.text1);
                tv1.setText("캐터피");
                TextView tv2 = (TextView) dialog.findViewById(R.id.text2);
                tv2.setText("풀과 애벌래를 조합하여 생성");

                ImageView iv = (ImageView) dialog.findViewById(R.id.image);
                iv.setImageResource(R.mipmap.monster4);

                dialog.show();
            }
        });

        monster5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dic_dialog);

                TextView tv1 = (TextView) dialog.findViewById(R.id.text1);
                tv1.setText("구구");
                TextView tv2 = (TextView) dialog.findViewById(R.id.text2);
                tv2.setText("바람과 새를 조합하여 생성");

                ImageView iv = (ImageView) dialog.findViewById(R.id.image);
                iv.setImageResource(R.mipmap.monster5);

                dialog.show();
            }
        });

        monster6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dic_dialog);

                TextView tv1 = (TextView) dialog.findViewById(R.id.text1);
                tv1.setText("???");
                TextView tv2 = (TextView) dialog.findViewById(R.id.text2);
                tv2.setText("??????????");

                ImageView iv = (ImageView) dialog.findViewById(R.id.image);
                iv.setImageResource(R.mipmap.monster6);

                dialog.show();
            }
        });

        return view;
    }
}
