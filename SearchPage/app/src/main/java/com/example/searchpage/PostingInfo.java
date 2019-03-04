package com.example.searchpage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostingInfo {
    public String storeName;
    public String imagePath;
    public int numLike;
    public Object postingTime;
    public String title;
    public ArrayList<String> tag;
    public String description;
    public String writerName;
    public String address;
    public String storeId;
    public String writerId;
    public float aver_star;
    public float[] detail_aver_star = {0,0,0,0};

    public PostingInfo() {
    }

    public PostingInfo(String storeName, String imagePath, int numLike, Object postingTime, String title, ArrayList<String> tag, String description, String writerName, String address, String storeId, String writerId, float aver_star, float[] detail_aver_star) {
        this.storeName = storeName;
        this.imagePath = imagePath;
        this.numLike = numLike;
        this.postingTime = postingTime;
        this.title = title;
        this.tag = tag;
        this.description = description;
        this.writerName = writerName;
        this.address = address;
        this.storeId = storeId;
        this.writerId = writerId;
        this.aver_star = aver_star;
        this.detail_aver_star = detail_aver_star;
    }

}