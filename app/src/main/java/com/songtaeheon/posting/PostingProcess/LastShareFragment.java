package com.songtaeheon.posting.PostingProcess;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.songtaeheon.posting.DataModel.KakaoStoreInfo;
import com.songtaeheon.posting.DataModel.PostingInfo;
import com.songtaeheon.posting.DataModel.StoreInfo;
import com.songtaeheon.posting.R;

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
    EditText text_title;
    ScrollView sv;
    RelativeLayout relLay;
    List<Float> detail_aver_star;


    KakaoStoreInfo kakaoStoreInfo;

    FirebaseStorage storage;
    FirebaseFirestore db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate!");
        //Store Search Fragment에서 받은 bundle데이터(네이터 api 검색 결과 선택 항목)을 받는다.
        if (getArguments() != null) {
            kakaoStoreInfo = getArguments().getParcelable("StoreData");
            Log.d(TAG, "kakaoStoreInfo : " + kakaoStoreInfo.place_name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_last_share, container, false);
        Log.d(TAG, "onCreateView!");

        text_description = view.findViewById(R.id.editTextDescription);
        text_title = view.findViewById(R.id.editTextTitle);
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
        /*
        * 함수 구조
        * 1. set the Posting data into postingInfo
        * 2. set the Store Data into storeInfo
        * 3. check the data in log cat
        * 4. add store info and posting info in dataBase!!!!
         * */


        //1. set the Posting data!!
        final PostingInfo postingInfo = new PostingInfo();
        postingInfo.writerId = currentUser.eMail;
        postingInfo.postingTime = Timestamp.now();
        postingInfo.description = text_description.getText().toString();
        postingInfo.title = text_title.getText().toString();
        detail_aver_star.add(mRatingBar1.getRating());//맛
        detail_aver_star.add(mRatingBar2.getRating());//가성비
        detail_aver_star.add(mRatingBar3.getRating());//서비스
        detail_aver_star.add(mRatingBar4.getRating());//분위기
        postingInfo.detail_aver_star = detail_aver_star;

        postingInfo.storeName = kakaoStoreInfo.place_name;
        postingInfo.address = kakaoStoreInfo.address_name;


        float sum =0 ;
        for(int i=0 ; i<detail_aver_star.size(); i++ ) {
            sum += detail_aver_star.get(i);
        }

        float aver_star = sum/detail_aver_star.size();
        postingInfo.aver_star = aver_star;
        postingInfo.imagePathInStorage = imagePathInStorage;

        //2. 해당 가게 정보가 이미 올라와있으면 받아온다. 없으면 새로 넣는다.

        //2. set the Store data!!
        //String mapxStr = kakaoStoreInfo.x;
        //String mapyStr = kakaoStoreInfo.y;

        StoreInfo storeInfo = new StoreInfo(kakaoStoreInfo.place_name, aver_star, kakaoStoreInfo.address_name, detail_aver_star, (int)Float.parseFloat(kakaoStoreInfo.x), (int)Float.parseFloat(kakaoStoreInfo.y));

        getAndSendData(storeInfo, postingInfo);


        //3. log cat에서 확인하기 위한 코드
        Log.d(TAG, "postingInfo - \n " +
                "imagePathInStorage : " + postingInfo.imagePathInStorage +"\n"+
                "postingTime : " + postingInfo.postingTime +"\n" +
                "description : "  + postingInfo.description +"\n"+
                "writerId : "  + postingInfo.writerId +"\n"+
                "aver_star : "  + postingInfo.aver_star +"\n");
        for(int i=0 ; i<detail_aver_star.size(); i++ ) {
            Log.d(TAG, "detail_aver_star ["+ i +"] : " + detail_aver_star.get(i));
        }

    }

    private void getAndSendData(final StoreInfo storeInfo, final PostingInfo postingInfo) {

        Log.d(TAG, "getStoreDataFromFirestore. storeName : " + storeInfo.name +", address :  " + storeInfo.address);

        db.collection("store")
                .whereEqualTo("name", postingInfo.storeName)
                .whereEqualTo("address", storeInfo.address)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            //조건에 해당하는게 없는지 확인. 없으면 새로 넣어준다.

                            if(task.getResult().isEmpty()) {
                                Log.d(TAG, "task.getResult : " + task.getResult().isEmpty());
                                putNewStoreInfo(storeInfo, postingInfo);
                            }


                            //있으면 업데이트 해준다.
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "get document!! : " + document.getId() + " => " + document.getData());
                                Map<String, Object> storeInfo = document.getData();
                                //postingInfo에 따라 별점 데이터를 바꾸어 준다.
                                storeInfo = changeStarInfo(storeInfo, postingInfo, document.getId());

                                //해당 도큐먼트의 내용을 바뀐 별점 내용으로 바꾸어주고, collection에 postingInfo를 넣어준다.
                                updatesFirestore(storeInfo, document.getId(), postingInfo);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });



    }

    //postingInfo의 별점을 storeInfo에 넣어준다.
    //평점 바꾸어주는 코드 필요
    private Map<String,Object> changeStarInfo(Map<String,Object> storeInfo, PostingInfo postingInfo, String id) {

        storeInfo.put("aver_star", postingInfo.aver_star);
        return storeInfo;
    }

    //add new store info and posting info in dataBase!!!!
    private void putNewStoreInfo(final StoreInfo storeInfo, final PostingInfo postingInfo) {
        db.collection("store").add(storeInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        //store collection document내부에 post컬렉션에 데이터를 넣는다.
                        db.collection("store").document(documentReference.getId())
                                .collection("post").add(postingInfo);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    //해당 도큐먼트의 내용을 바뀐 별점 내용으로 바꾸어주고, collection에 postingInfo를 넣어준다.
    private void updatesFirestore(Map<String,Object> storeInfo, String id, PostingInfo postingInfo) {
        db.collection("store").document(id)
                .set(storeInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

        //db에 데이터를 넣는 코드 필요
        db.collection("store").document(id).collection("post")
                .add(postingInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "store컬렉션 내부 post컬렉션 내부 포스팅 ID: " + documentReference.getId());
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