package com.songtaeheon.posting;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.database.ServerValue;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.songtaeheon.posting.DataModel.NaverStoreInfo;
import com.songtaeheon.posting.DataModel.PostingInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.songtaeheon.posting.MainActivity.currentUser;


public class LastShareFragment extends Fragment {

    private final String TAG = "TAGLastShareFragment";
    RatingBar mRatingBar1;
    RatingBar mRatingBar2;
    RatingBar mRatingBar3;
    RatingBar mRatingBar4;
    TextView mStarText1;
    TextView mStarText2;
    TextView mStarText3;
    TextView mStarText4;
    EditText text_description;
    ScrollView sv;
    RelativeLayout relLay;
    List<Float> detail_aver_star;


    NaverStoreInfo naverStoreInfo;

    FirebaseStorage storage;
    FirebaseFirestore db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate!");
        //Store Search Fragment에서 받은 bundle데이터(네이터 api 검색 결과 선택 항목)을 받는다.
        if (getArguments() != null) {
            naverStoreInfo = getArguments().getParcelable("StoreData");
            Log.d(TAG, "naverStoreInfo : " + naverStoreInfo.title);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_last_share, container, false);
        Log.d(TAG, "onCreateView!");

        text_description = view.findViewById(R.id.editText);
        mRatingBar1 = view.findViewById(R.id.ratingBar1);
        mRatingBar2 = view.findViewById(R.id.ratingBar2);
        mRatingBar3 = view.findViewById(R.id.ratingBar3);
        mRatingBar4 = view.findViewById(R.id.ratingBar4);
        mStarText1 = view.findViewById(R.id.starText1);
        mStarText2 = view.findViewById(R.id.starText2);
        mStarText3 = view.findViewById(R.id.starText3);
        mStarText4 = view.findViewById(R.id.starText4);



        detail_aver_star = new ArrayList<Float>() {};
        String text = text_description.getText().toString();
        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();

        //editText선택시 스크롤되도록
        sv = view.findViewById(R.id.scrollView);
        relLay = view.findViewById(R.id.relLayoutInScroll);
        text_description.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    sv.smoothScrollTo(0, relLay.getHeight());

                }
            }
        });

        //back 버튼
        ImageView backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onclick : closing the LastShare fragment, back to restaurant search fragment");
                //back button 기능
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStackImmediate();

            }
        });

        //share 버튼
        TextView completeButton = view.findViewById(R.id.tvNext);
        completeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onclick : share!!!!!");
                //이미지, 가게정보, 태그 등을 파이어베이스로 올리는 함수 실행
                postingOnFirebaseDatabase();

                //다끝나면 액티비티 종료
                getActivity().finish();
            }
        });

        //Star Rating Bar setup
        setupStarRatingBar();
        return view;
    }



    //ratingBar Listener
    private void setupStarRatingBar(){

        //맛
        mRatingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                setStarText(mRatingBar1, mStarText1);

            }
        });

        //가성비
        mRatingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                setStarText(mRatingBar2, mStarText2);

            }
        });

        //서비스
        mRatingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                setStarText(mRatingBar3, mStarText3);

            }
        });

        //분위기
        mRatingBar4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                setStarText(mRatingBar4, mStarText4);

            }
        });

    }
    private void setStarText(RatingBar b, TextView v){ //변경
        v.setText(String.valueOf(v));
        switch ((int) b.getRating()) {
            case 1:
                v.setText("최악이에요!");
                break;
            case 2:
                v.setText("별로에요...");
                break;
            case 3:
                v.setText("괜찮았어요!");
                break;
            case 4:
                v.setText("좋았어요!!");
                break;
            case 5:
                v.setText("정말 최고입니다!!");
                break;
            default:
                v.setText("");
        }
    }


    //사진 upload 후, 사진 업로드가 완료되면 업로드된 url을 가져온 후, db에 다른 정보들과 함께 세팅한다.
    private void postingOnFirebaseDatabase() {
        String path = GalleryFragment.selectedImgURL;
        Log.d(TAG, "uploading Photo to firebase storage. path : " + path);

        //사진 업로드
        StorageReference storageRef = storage.getReferenceFromUrl("gs://posting-67b8f.appspot.com");
        Uri file = Uri.fromFile(new File(path));
        final StorageReference photoRef = storageRef.child("images/"+file.getLastPathSegment());
        UploadTask uploadTask = photoRef.putFile(file);


        //get upload Url
        Task<Uri> urlTask = uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.w(TAG, "sending image to firebase is failed");
                // Handle unsuccessful uploads
            }
        }).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL
                return photoRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    String downloadUriStr = downloadUri.toString();
                    Log.d(TAG, "Getting upload url is completed. download url : " + downloadUriStr);

                    //전달해야하는 데이터 세팅 및 데이터 전달
                    setAndSendPosting(downloadUriStr);

                } else {
                    // Handle failures
                    Log.w(TAG, "Getting upload url is failed.");
                }
            }
        });
    }
    private void setAndSendPosting(String imagePathInStorage) {
        //set the data!!
        PostingInfo postingInfo = new PostingInfo();
        postingInfo.writerId = currentUser.eMail;
        postingInfo.postingTime = Timestamp.now();
        postingInfo.description = text_description.getText().toString();
        detail_aver_star.add(mRatingBar1.getRating());//맛
        detail_aver_star.add(mRatingBar2.getRating());//가성비
        detail_aver_star.add(mRatingBar3.getRating());//서비스
        detail_aver_star.add(mRatingBar4.getRating());//분위기
        postingInfo.detail_aver_star = detail_aver_star;

        postingInfo.storeName = naverStoreInfo.title;
        postingInfo.address = naverStoreInfo.address;


        float sum =0 ;
        for(int i=0 ; i<detail_aver_star.size(); i++ ) {
            sum += detail_aver_star.get(i);
        }
        postingInfo.aver_star = sum/detail_aver_star.size();
        postingInfo.imagePathInStorage = imagePathInStorage;

        Log.d(TAG, "postingInfo - \n " +
                "imagePathInStorage : " + postingInfo.imagePathInStorage +"\n"+
                "postingTime : " + postingInfo.postingTime +"\n" +
                "description : "  + postingInfo.description +"\n"+
                "writerId : "  + postingInfo.writerId +"\n"+
                "aver_star : "  + postingInfo.aver_star +"\n");
        for(int i=0 ; i<detail_aver_star.size(); i++ ) {
            Log.d(TAG, "detail_aver_star ["+ i +"] : " + detail_aver_star.get(i));
        }

        //add dataBase!!!!
        db.collection("post").add(postingInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }
}