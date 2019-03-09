package com.songtaeheon.posting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.songtaeheon.posting.DataModel.PostingInfo;
import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> { // 가로 리사이클러뷰를 위한 어뎁터

    private ArrayList<PostingInfo> list;
    private Context mContext;

    public SectionListDataAdapter(Context context, ArrayList<PostingInfo> mList) {
        this.list = mList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.polar_style, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        PostingInfo singleItem = list.get(i);

        holder.mainView.setText(singleItem.getStoreName());


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

            this.mainView = (TextView) view.findViewById(R.id.textmain);
            this.imageView = (ImageView) view.findViewById(R.id.imagefood);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), mainView.getText(), Toast.LENGTH_SHORT).show();

                }
            });


        }

    }

}
