package com.songtaeheon.posting.DataModel;

import android.os.Parcel;
import android.os.Parcelable;


//StoreSearch Fragment에서 LastShareFragment로 데이터를 전달하기 위해 parcelable구현
public class NaverStoreInfo implements Parcelable {
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

    public NaverStoreInfo(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(telephone);
        dest.writeString(address);
        dest.writeString(roadAddress);
        dest.writeString(category);
        dest.writeString(link);
        dest.writeInt(mapx);
        dest.writeInt(mapy);

    }

    private void readFromParcel(Parcel in){
        title = in.readString();
        description = in.readString();
        telephone = in.readString();
        address = in.readString();
        address = in.readString();
        roadAddress = in.readString();
        category = in.readString();
        link = in.readString();
        mapx = in.readInt();
        mapy = in.readInt();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public NaverStoreInfo createFromParcel(Parcel in) {
            return new NaverStoreInfo(in);
        }

        public NaverStoreInfo[] newArray(int size) {
            return new NaverStoreInfo[size];
        }
    };
}