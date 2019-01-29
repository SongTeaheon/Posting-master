package com.example.startaste;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProfileStatus extends Fragment {

    private int value0, value1, value2, value3, value4, value5, value6, value7, value8;
    //private int value00, value01, value02, value03, value04, value05, value06, value07, value08;

    TextView userPoint, textView11, textView12, textView13, textView14, textView15, textView16,
        textView17, textView18;

    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5,
        progressBar6, progressBar7, progressBar8;

    Button button1, button2, button3, button4, button5, button6, button7, button8,
        buttonReturn, buttonDecide;

    @Override
    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_status, null);

        userPoint = (TextView) view.findViewById(R.id.userPoint);

        textView11 = (TextView) view.findViewById(R.id.textView11);
        textView12 = (TextView) view.findViewById(R.id.textView12);
        textView13 = (TextView) view.findViewById(R.id.textView13);
        textView14 = (TextView) view.findViewById(R.id.textView14);
        textView15 = (TextView) view.findViewById(R.id.textView15);
        textView16 = (TextView) view.findViewById(R.id.textView16);
        textView17 = (TextView) view.findViewById(R.id.textView17);
        textView18 = (TextView) view.findViewById(R.id.textView18);

        progressBar1 = (ProgressBar) view.findViewById(R.id.prograssBar1);
        progressBar2 = (ProgressBar) view.findViewById(R.id.prograssBar2);
        progressBar3 = (ProgressBar) view.findViewById(R.id.prograssBar3);
        progressBar4 = (ProgressBar) view.findViewById(R.id.prograssBar4);
        progressBar5 = (ProgressBar) view.findViewById(R.id.prograssBar5);
        progressBar6 = (ProgressBar) view.findViewById(R.id.prograssBar6);
        progressBar7 = (ProgressBar) view.findViewById(R.id.prograssBar7);
        progressBar8 = (ProgressBar) view.findViewById(R.id.prograssBar8);

        button1 = (Button) view.findViewById(R.id.button1);
        button2 = (Button) view.findViewById(R.id.button2);
        button3 = (Button) view.findViewById(R.id.button3);
        button4 = (Button) view.findViewById(R.id.button4);
        button5 = (Button) view.findViewById(R.id.button5);
        button6 = (Button) view.findViewById(R.id.button6);
        button7 = (Button) view.findViewById(R.id.button7);
        button8 = (Button) view.findViewById(R.id.button8);

        buttonReturn = (Button) view.findViewById(R.id.buttonReturn);
        buttonDecide = (Button) view.findViewById(R.id.buttonDecide);

        value0 = 15;// 15대신 서버에서 유저데이터 가져오기
        value1 = 0;// 이 아래 0들도 다 유저데이터
        value2 = 0;
        value3 = 0;
        value4 = 0;
        value5 = 0;
        value6 = 0;
        value7 = 0;
        value8 = 0;// 여기까지

        userPoint.setText(value0 + "");
        textView11.setText(value1 + "");
        textView12.setText(value2 + "");
        textView13.setText(value3 + "");
        textView14.setText(value4 + "");
        textView15.setText(value5 + "");
        textView16.setText(value6 + "");
        textView17.setText(value7 + "");
        textView18.setText(value8 + "");

        progressBar1.setProgress(value1);
        progressBar2.setProgress(value2);
        progressBar3.setProgress(value3);
        progressBar4.setProgress(value4);
        progressBar5.setProgress(value5);
        progressBar6.setProgress(value6);
        progressBar7.setProgress(value7);
        progressBar8.setProgress(value8);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(value0 != 0) {
                   userPoint.setText(--value0 + "");
                   textView11.setText(++value1 + "");
                   progressBar1.setProgress(value1);
               }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value0 != 0) {
                    userPoint.setText(--value0 + "");
                    textView12.setText(++value2 + "");
                    progressBar2.setProgress(value2);
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value0 != 0) {
                    userPoint.setText(--value0 + "");
                    textView13.setText(++value3 + "");
                    progressBar3.setProgress(value3);
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value0 != 0) {
                    userPoint.setText(--value0 + "");
                    textView14.setText(++value4 + "");
                    progressBar4.setProgress(value4);
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value0 != 0) {
                    userPoint.setText(--value0 + "");
                    textView15.setText(++value5 + "");
                    progressBar5.setProgress(value5);
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value0 != 0) {
                    userPoint.setText(--value0 + "");
                    textView16.setText(++value6 + "");
                    progressBar6.setProgress(value6);
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value0 != 0) {
                    userPoint.setText(--value0 + "");
                    textView17.setText(++value7 + "");
                    progressBar7.setProgress(value7);
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value0 != 0) {
                    userPoint.setText(--value0 + "");
                    textView18.setText(++value8 + "");
                    progressBar8.setProgress(value8);
                }
            }
        });

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value0 = 15;
                value1 = 0; value2 = 0; value3 = 0; value4 = 0;
                value5 = 0; value6 = 0; value7 = 0; value8 = 0;

                userPoint.setText(value0 + "");
                textView11.setText(value1 + "");
                textView12.setText(value2 + "");
                textView13.setText(value3 + "");
                textView14.setText(value4 + "");
                textView15.setText(value5 + "");
                textView16.setText(value6 + "");
                textView17.setText(value7 + "");
                textView18.setText(value8 + "");

                progressBar1.setProgress(value1);
                progressBar2.setProgress(value2);
                progressBar3.setProgress(value3);
                progressBar4.setProgress(value4);
                progressBar5.setProgress(value5);
                progressBar6.setProgress(value6);
                progressBar7.setProgress(value7);
                progressBar8.setProgress(value8);
            }
        });


        return view;

    }


}
