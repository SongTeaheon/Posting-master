package com.songtaeheon.posting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.songtaeheon.posting.DataModel.PostingInfo;
import com.songtaeheon.posting.DataModel.StoreInfo;
import com.songtaeheon.posting.Utils.GlideApp;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> { // 가로 리사이클러뷰를 위한 어뎁터

    private ArrayList<PostingInfo> list;
    private Context mContext;
    private static final String TAG = "TAGSectionViewAdapter";
    FirebaseFirestore db;
    FirebaseStorage storage;
    StorageReference storageReference;



    public SectionListDataAdapter(Context context,String postId, ArrayList<PostingInfo> mList) {
        this.list = mList;
        this.mContext = context;
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        //getPostDataFromCloud(postId);
    }



    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder");

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.polar_style, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        Log.d(TAG, "onBindViewHolder");

        PostingInfo singleItem = list.get(i);

        holder.mainView.setText(singleItem.getTitle());

        Log.d(TAG, "downloadImageFromFirebaseStorage : " + singleItem.imagePathInStorage);


        StorageReference fileReference = storage.getReferenceFromUrl(singleItem.imagePathInStorage);
        GlideApp.with(mContext).load(fileReference).into(holder.imageView);

       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView mainView;


        public SingleItemRowHolder(View view) {
            super(view);
            Log.d(TAG, "singleItemRowHolder");

            this.mainView = (TextView) view.findViewById(R.id.textmain);
            this.imageView = (ImageView) view.findViewById(R.id.imagefood);


            //이미지 선택시 반응
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), mainView.getText(), Toast.LENGTH_SHORT).show();

                }
            });


        }

    }




}
