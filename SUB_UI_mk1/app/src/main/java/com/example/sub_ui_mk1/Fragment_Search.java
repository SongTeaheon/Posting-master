package com.example.sub_ui_mk1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fragment_Search extends Fragment {

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    RecyclerView mRecyclerView;
    ArrayList<ArrayList<PolarInfo>> listVertical;

    @Override
    public void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_search, null);

        editSearch = (EditText) view.findViewById(R.id.editText);
        listView = (ListView) view.findViewById(R.id.listView);


        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(list, getActivity());

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });

        list.clear();

        ArrayList<PolarInfo> subList1 = new ArrayList<>();
        subList1.add(new PolarInfo(10, "서울", "달방", R.mipmap.dalbang, "마카롱 존맛", "♥54", "2019.05.22"));
        subList1.add(new PolarInfo(11, "서울", "달방", R.mipmap.dalbang, "마카롱 존맛탱", "♥154", "2019.05.25"));
        subList1.add(new PolarInfo(12, "서울", "달방", R.mipmap.dalbang, "마카롱 JMT", "♥55", "2019.01.21"));
        subList1.add(new PolarInfo(13, "서울", "달방", R.mipmap.dalbang, "마카롱 JMT!", "♥55", "2019.01.21"));
        subList1.add(new PolarInfo(14, "서울", "달방", R.mipmap.dalbang, "마카롱 JMT!!!", "♥55", "2019.01.21"));

        ArrayList<PolarInfo> subList2 = new ArrayList<>();
        subList2.add(new PolarInfo(20, "서울", "무쏘", R.mipmap.musso, "역시 소고기...", "♥54", "2019.05.11"));
        subList2.add(new PolarInfo(21, "서울", "무쏘", R.mipmap.musso, "역시 소고기!!!", "♥514", "2019.01.11"));
        subList2.add(new PolarInfo(22, "서울", "무쏘", R.mipmap.musso, "역시 소고기 흑흑", "♥114", "2019.05.31"));
        subList2.add(new PolarInfo(23, "서울", "무쏘", R.mipmap.musso, "역시 소고기 흑흑", "♥114", "2019.05.31"));
        subList2.add(new PolarInfo(24, "서울", "무쏘", R.mipmap.musso, "역시 소고기는 진리..", "♥114", "2019.05.31"));

        ArrayList<PolarInfo> subList3 = new ArrayList<>();
        subList3.add(new PolarInfo(30, "송주", "송주불냉면", R.mipmap.nengmyun, "겁나 맵다...", "♥34", "2019.04.11"));
        subList3.add(new PolarInfo(31, "송주", "송주불냉면", R.mipmap.nengmyun, "겁나 맵다.!", "♥324", "2019.01.11"));

        ArrayList<PolarInfo> subList4 = new ArrayList<>();
        subList4.add(new PolarInfo(40, "서울", "피자스쿨", R.mipmap.pizzaschool, "저렴둥이 피자", "♥64", "2019.09.01"));
        subList4.add(new PolarInfo(41, "서울", "피자스쿨", R.mipmap.pizzaschool, "저렴둥이 피자!", "♥64", "2019.09.01"));
        subList4.add(new PolarInfo(42, "서울", "피자스쿨", R.mipmap.pizzaschool, "저렴둥이 피자!!", "♥64", "2019.09.01"));
        subList4.add(new PolarInfo(43, "서울", "피자스쿨", R.mipmap.pizzaschool, "저렴둥이 피자!!!", "♥64", "2019.09.01"));

        ArrayList<PolarInfo> subList5 = new ArrayList<>();
        subList5.add(new PolarInfo(50, "서울", "반지하돈부리", R.mipmap.sakedong, "연어 넘조아...", "♥54", "2019.04.30"));
        subList5.add(new PolarInfo(51, "서울", "반지하돈부리", R.mipmap.sakedong, "연.어.조.아", "♥54", "2019.09.04"));
        subList5.add(new PolarInfo(52, "서울", "반지하돈부리", R.mipmap.sakedong, "연어 갓갓갓", "♥54", "2019.04.30"));
        subList5.add(new PolarInfo(53, "서울", "반지하돈부리", R.mipmap.sakedong, "사케동 러버", "♥54", "2019.04.03"));
        subList5.add(new PolarInfo(54, "서울", "반지하돈부리", R.mipmap.sakedong, "연어란...", "♥54", "2019.04.10"));
        subList5.add(new PolarInfo(55, "서울", "반지하돈부리", R.mipmap.sakedong, "연어 넘조아ㅜㅜ.", "♥54", "2019.04.14"));



        listVertical = new ArrayList<>();
        listVertical.add(subList1);
        listVertical.add(subList2);
        listVertical.add(subList3);
        listVertical.add(subList4);
        listVertical.add(subList5);

        mRecyclerView = view.findViewById(R.id.recycler_view);

        initUi();



        return view;
    }

    private void initUi() {
        mRecyclerView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        VerticalRecyclerAdapter adapter = new VerticalRecyclerAdapter(listVertical);
        mRecyclerView.setAdapter(adapter);


        adapter.SetOnItemClickListener(new HorizontalRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "click " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                String value = "";
                if(position / 10 == 1) {
                    value = "달방";
                } else if(position / 10 == 2) {
                    value = "무쏘";
                } else if(position / 10 == 3) {
                    value = "송주불냉면";
                } else if(position / 10 == 4) {
                    value = "피자스쿨";
                } else if(position / 10 == 5) {
                    value = "반지하돈부리";
                }

                Bundle args = new Bundle();
                args.putString("key", value);

                CustomDialog customDialog = new CustomDialog();
                customDialog.setArguments(args);

                customDialog.show(getFragmentManager(), "tag");

                Toast.makeText(getActivity(), "long click " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();
        listView.setVisibility(View.VISIBLE);

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) { listView.setVisibility(View.INVISIBLE);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    private void settingList(){
        list.add("달방");
        list.add("반지하돈부리");
        list.add("무쏘");
        list.add("피자스쿨");
        list.add("송주불냉면");
    }
}