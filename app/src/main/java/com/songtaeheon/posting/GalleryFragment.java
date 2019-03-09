package com.songtaeheon.posting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.songtaeheon.posting.Utils.FilePaths;
import com.songtaeheon.posting.Utils.FileSearch;
import com.songtaeheon.posting.Utils.GridImageAdapterForGallery;

import java.util.ArrayList;


public class GalleryFragment extends Fragment {
    private static final String TAG = "TAGGalleryFragment";
    private static final String mAppend = "file:/";

    public static String selectedImgURL;

    private GridView gridView;
    private Spinner directorySpinner;
    private boolean isItemSelected = false;
    private View selectedView;

    private ArrayList<String> directories;
    ArrayList<String> imgURLs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "OnCreateView : started");
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        gridView = view.findViewById(R.id.gridView);
        directorySpinner = view.findViewById(R.id.spinnerDirectory);
        directories = new ArrayList<>();

        //x버튼
        ImageView shareClose = view.findViewById(R.id.backButton);
        shareClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onclick : closing the gallery fragment");
                getActivity().finish();
            }
        });

        //next 버튼
        TextView nextScreen = view.findViewById(R.id.tvNext);
        nextScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onclick : navigating to the storeSearch screen");
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.relLayout1, new StoreSearchFragment());
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        //spinner setting
        init();

        return view;
    }


    //spinner Setting
    private void init(){
        //check for other folders inside "/storage/emulated/0/pictures
        if(FileSearch.getDirectoryPaths(FilePaths.PICTURES) != null){
            directories = FileSearch.getDirectoryPaths(FilePaths.PICTURES);

        }else{
            return;
        }

        Log.d(TAG, "init() : set adapter on directorySpinner");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, directories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        directorySpinner.setAdapter(adapter);



        directorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: selected : " + directories.get(i));
                //images 세팅
                setupGridView(directories.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setupGridView(FilePaths.CAMERA);
            }
        });


    }

    //grid View setting
    private void setupGridView(String selectedDirectory){
        Log.d(TAG, "setupGridView: directory chosen : " + selectedDirectory);
        imgURLs = FileSearch.getFilePaths(selectedDirectory);


        //use the grid adapter to adapter the images to gridView
        GridImageAdapterForGallery adapter = new GridImageAdapterForGallery(getActivity(), R.layout.layout_grid_imageview, mAppend, imgURLs);
        gridView.setAdapter(adapter);




        //set click the image
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick : selected an image : " + imgURLs.get(i));

                //다른 이미지가 선택된 상태라면 그 선택된 이미지의 바탕색을 흰색으로 바꾼다.
                if(isItemSelected){
                    selectedView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                //다른 이미지가 선택될 경우 이전 이미지의 바탕색을 바꿔야하므로 저장해놓는다.
                selectedView = view;
                isItemSelected = true;

                //선택된 이미지의 바탕색을 바꾸어놓는다.
                view.setBackgroundColor(Color.parseColor("#000000"));
                selectedImgURL = imgURLs.get(i);


            }
        });
    }





}
