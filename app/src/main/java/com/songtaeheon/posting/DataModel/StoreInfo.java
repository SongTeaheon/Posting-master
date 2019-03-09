package com.songtaeheon.posting.DataModel;

import java.util.ArrayList;
import java.util.List;

public class StoreInfo {
    public String name;
    public float aver_star;
    public String address;
    public float[] detail_aver_star = {0,0,0,0};
    private ArrayList<PostingInfo> allItemsInSection;

    public StoreInfo() {

    }

    public StoreInfo(String name,
                     float aver_star,
                     String address,
                     float[] detail_aver_star) {
        this.name = name;
        this.aver_star = aver_star;
        this.address = address;
        this.detail_aver_star = detail_aver_star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PostingInfo> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<PostingInfo> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }
}