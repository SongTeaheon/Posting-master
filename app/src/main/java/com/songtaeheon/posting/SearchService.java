package com.songtaeheon.posting;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SearchService {

    public static final String API_URL = "https://openapi.naver.com/v1/search/";

    @GET("local.json")
    Call<JsonObject> getUserRepositories(@Header("X-Naver-Client-Id") String id,
                                         @Header("X-Naver-Client-Secret") String secret,
                                         @Query("query") String searchWord);

}
