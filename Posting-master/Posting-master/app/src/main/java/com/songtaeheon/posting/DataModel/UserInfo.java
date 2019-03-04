package com.songtaeheon.posting.DataModel;

public class UserInfo {
    public String eMail;
    public String nickname;
    public String password;
    public String profileImage;
    public int totalLike;

    public UserInfo(String eMail, String nickName, String password, String profileImage, int totalLike) {
        this.eMail = eMail;
        this.nickname = nickName;
        this.password = password;
        this.profileImage = profileImage;
        this.totalLike = totalLike;
    }

    public UserInfo() {
    }
}