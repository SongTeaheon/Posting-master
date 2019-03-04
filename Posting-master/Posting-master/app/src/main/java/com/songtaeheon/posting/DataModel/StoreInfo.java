package com.songtaeheon.posting.DataModel;

import java.util.List;

public class StoreInfo {
    public String name;
    public float aver_star;
    public String address;
    public float[] detail_aver_star = {0,0,0,0};

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
}