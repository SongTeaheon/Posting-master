package com.songtaeheon.posting.DataModel;

import java.util.ArrayList;
import java.util.List;

public class StoreInfo {
    public String name;
    public float aver_star;
    public String address;
    public List<Float> detail_aver_star;
    public int coordinates_x;
    public int coordinates_y;
    private ArrayList<PostingInfo> allItemsInSection;

    public StoreInfo() {

    }

    public StoreInfo(String name,
                     float aver_star,
                     String address,
                     List<Float> detail_aver_star,
                     int coordinates_x,
                     int coordinates_y) {
        this.name = name;
        this.aver_star = aver_star;
        this.address = address;
        this.detail_aver_star = detail_aver_star;
        this.coordinates_x = coordinates_x;
        this.coordinates_y = coordinates_y;
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