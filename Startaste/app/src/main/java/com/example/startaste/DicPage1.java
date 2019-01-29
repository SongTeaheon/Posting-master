package com.example.startaste;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DicPage1 extends Fragment {

    ImageButton monster1;
    ImageButton monster2;
    ImageButton monster3;

    @Override
    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.dic_page1, null);

        monster1 = (ImageButton) view.findViewById(R.id.monster1);
        monster2 = (ImageButton) view.findViewById(R.id.monster2);
        monster3 = (ImageButton) view.findViewById(R.id.monster3);

        monster1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dic_dialog);

                TextView tv1 = (TextView) dialog.findViewById(R.id.text1);
                tv1.setText("이상해씨");
                TextView tv2 = (TextView) dialog.findViewById(R.id.text2);
                tv2.setText("풀과 괴물을 조합하여 생성");

                ImageView iv = (ImageView) dialog.findViewById(R.id.image);
                iv.setImageResource(R.mipmap.monster1);

                dialog.show();
            }
        });

        monster2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dic_dialog);

                TextView tv1 = (TextView) dialog.findViewById(R.id.text1);
                tv1.setText("파이리");
                TextView tv2 = (TextView) dialog.findViewById(R.id.text2);
                tv2.setText("불과 용을 조합하여 생성");

                ImageView iv = (ImageView) dialog.findViewById(R.id.image);
                iv.setImageResource(R.mipmap.monster2);

                dialog.show();
            }
        });

        monster3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dic_dialog);

                TextView tv1 = (TextView) dialog.findViewById(R.id.text1);
                tv1.setText("꼬부기");
                TextView tv2 = (TextView) dialog.findViewById(R.id.text2);
                tv2.setText("물과 거북을 조합하여 생성");

                ImageView iv = (ImageView) dialog.findViewById(R.id.image);
                iv.setImageResource(R.mipmap.monster3);

                dialog.show();
            }
        });

        return view;
    }
}
