package com.example.searchpage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewLayout extends Fragment {

    private TextView storeName;
    private ImageView imageView;
    private TextView numLike;
    private TextView postingTime;
    private TextView titleText;
    private TextView tags;
    private TextView description;
    private TextView writerName;
    private TextView address;
    private TextView storeId;
    private TextView writerId;
    private TextView aver_star;
    FirebaseFirestore firestore;

    @Override
    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.layout_view, null);

        storeName = (TextView) view.findViewById(R.id.storeName);
        imageView = (ImageView) view.findViewById(R.id.imagefood);
        numLike = (TextView) view.findViewById(R.id.numLike);
        postingTime = (TextView) view.findViewById(R.id.postingTime);
        titleText = (TextView) view.findViewById(R.id.titleText);
        tags = (TextView) view.findViewById(R.id.tags);
        description = (TextView) view.findViewById(R.id.description);
        writerName = (TextView) view.findViewById(R.id.writerName);
        address = (TextView) view.findViewById(R.id.address);
        storeId = (TextView) view.findViewById(R.id.storeId);
        writerId = (TextView) view.findViewById(R.id.writerId);
        aver_star = (TextView) view.findViewById(R.id.aver_star);

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        return view;
    }
}
