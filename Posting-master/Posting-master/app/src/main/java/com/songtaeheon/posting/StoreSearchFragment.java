package com.songtaeheon.posting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.songtaeheon.posting.DataModel.NaverStoreInfo;
import com.songtaeheon.posting.DataModel.StoreInfo;
import com.songtaeheon.posting.Utils.RecyclerviewAdapterForShare;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.songtaeheon.posting.SearchService.API_URL;


public class StoreSearchFragment extends Fragment {
    private static final String TAG = "TAGStoreResearchFrag";
    private final String naverApiId = "B6m_wV3mDimRZaxT8fZe";
    private final String naverApiSecret = "rb2Or79uka";

    Retrofit retrofit;
    SearchService service;

    EditText searchWordText;
    String searchWord;

    RecyclerView mRecyclerView;
    ArrayList<NaverStoreInfo> storeInfoArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "OnCreateView : started");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_search, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerview);

        storeInfoArrayList = new ArrayList<>();





        //back 버튼
        ImageView backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onclick : closing the restaurantn research fragment, back to gallery fragment");
                //back button 기능
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStackImmediate();

            }
        });

        //next 버튼
        TextView nextScreen = view.findViewById(R.id.tvNext);
        nextScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onclick : navigating to the final share screen");
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.relLayout1, new LastShareFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        //search 버튼
        searchWordText = view.findViewById(R.id.searchWord);
        Button searchButton = view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                searchWord = searchWordText.getText().toString();
                Log.d(TAG, "search button clicked. searchWord : "+ searchWord);
                requestSearchApi(searchWord);
                setupRecyclerViewContents();

            }
        });

        //recyclerview setup
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);


        return view;
    }

    private void setupRecyclerViewContents() {
        RecyclerviewAdapterForShare myAdapter = new RecyclerviewAdapterForShare(getActivity(), storeInfoArrayList);
        mRecyclerView.setAdapter(myAdapter);
    }


    private void requestSearchApi(String searchWord){

        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(SearchService.class);
        Call<JsonObject> request = service.getUserRepositories(naverApiId, naverApiSecret, searchWord);
        request.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "request enqueue is Successed : " + response.toString());
                Log.d(TAG, "request enqueue is Successed : " + response.body().toString());
                storeInfoArrayList = parseJsonToStoreInfo(response.body());
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "request enqueue is failed : w : " + t.toString());
                t.printStackTrace();
            }
        });


    }

    private ArrayList<NaverStoreInfo> parseJsonToStoreInfo(JsonObject jsonObject) {
        ArrayList<NaverStoreInfo> dataList = new ArrayList<>();
        Gson gson = new Gson();

        JsonArray jsonArray = (JsonArray) jsonObject.get("items");
        for(int i=0 ; i<jsonArray.size(); i++){
            NaverStoreInfo object = gson.fromJson(jsonArray.get(i), NaverStoreInfo.class);
            dataList.add(object);
            Log.d(TAG, "object.title : " + object.title);
        }
        return dataList;

    }
}
