package com.songtaeheon.posting.DataModel;

public class NaverStoreInfo {
    public String title;
    public String description;
    public String telephone;
    public String address;
    public String roadAddress;
    public int mapx;
    public int mapy;
    public String category;
    public String link;

    public NaverStoreInfo(String title, String description, String telephone, String address, String roadAddress, int mapx, int mapy, String category, String link) {
        this.title = title;
        this.description = description;
        this.telephone = telephone;
        this.address = address;
        this.roadAddress = roadAddress;
        this.mapx = mapx;
        this.mapy = mapy;
        this.category = category;
        this.link = link;
    }

    public NaverStoreInfo(String title, String address, String category) {
        this.title = title;
        this.address = address;
        this.category = category;
    }

}