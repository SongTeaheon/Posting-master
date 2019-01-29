package com.example.startaste;

import android.graphics.Bitmap;
import android.net.Uri;

public class StoreInfo {
    public int itemImage;
    public String itemName;
    public int itemCost;
    public String itemLevel;

    public StoreInfo(int itemImage, String itemName, int itemCost, String itemLevel) {
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemLevel = itemLevel;
    }
}
