package com.example.sub_ui_mk1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-08-07.
 */

public class CustomDialog extends DialogFragment{

    private Fragment fragment;

    public CustomDialog() {

    }

    @Override
    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.custom_dialog, null);

        TextView textView = (TextView) view.findViewById(R.id.textView0);

        Bundle margs = getArguments();
        String mValue = margs.getString("key");

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        textView.setText(mValue);

        return view;
    }
}

