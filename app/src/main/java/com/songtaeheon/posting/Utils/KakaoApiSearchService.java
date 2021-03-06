package com.songtaeheon.posting.Utils;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface KakaoApiSearchService {

    public static final String API_URL = "https://dapi.kakao.com/v2/local/search/";

    @GET("keyword.json")
    Call<JsonObject> getUserRepositories(@Header("Authorization") String id,
                                         @Query("query") String searchWord,
                                         @Query("category_group_code") String code);

}
