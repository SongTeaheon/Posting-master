package com.songtaeheon.posting.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.songtaeheon.posting.R;

import java.util.ArrayList;

public class GridImageAdapterForGallery extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    int layoutResource;
    String mAppend;
    ArrayList<String> imgURLs;

    public GridImageAdapterForGallery(Context context, int layoutResource, String append, ArrayList<String> imgURLs) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
        this.layoutResource = layoutResource;
        mAppend = append;
        this.imgURLs = imgURLs;
    }

    @Override
    public int getCount() {
        return imgURLs.size();
    }

    @Override
    public String getItem(int position) {
        return imgURLs.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //convertView가 아무것도 가리키지 않는 경우 grid view 하나하나의 아이템에 들어갈 layout을 가리키도록 한다.
        if (convertView == null) {
            convertView = mInflater.inflate(layoutResource, null);
        }

        ImageView gridImageView = convertView.findViewById(R.id.gridImageView);

        //이미지 로더를 생성하고 세팅해야한다.
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        imageLoader.displayImage("file:/" + imgURLs.get(position), gridImageView);



        return convertView;
    }

}
