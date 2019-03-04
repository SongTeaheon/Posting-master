package com.songtaeheon.posting.DataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostingInfo {
    public String storeName;
    public String imagePathInStorage;
    public int numLike;
    public Object postingTime;
    public String title;
    public Map<String, Boolean> tag;
    public String description;
    public String writerName;
    public String address;
    public String storeId;
    public String writerId;
    public float aver_star;
    public List<Float> detail_aver_star; //{맛, 가성비, 서비스, 분위기}

    public PostingInfo() {
    }


}
