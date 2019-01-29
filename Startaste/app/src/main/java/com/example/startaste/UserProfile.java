package com.example.startaste;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.media.Image;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageView);
        TextView userLv = (TextView) findViewById(R.id.userLevel);
        TextView userName = (TextView) findViewById(R.id.userName);
        TextView userStyle = (TextView) findViewById(R.id.userStyle);
        final Button buttonFollow = (Button) findViewById(R.id.buttonFollow);
        TextView textViewLv = (TextView) findViewById(R.id.lvpercent);
        TextView textViewLk = (TextView) findViewById(R.id.userLank);
        Intent intent = getIntent();

        TabLayout tablayout;
        ViewPager viewPager;

        buttonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonFollow.getText() == "팔로잉") {
                    buttonFollow.setText("팔로우");
                } else buttonFollow.setText("팔로잉");
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            final String[] profiles = new String[] {"사진촬영", "앨범에서 사진 선택", "기본 이미지로 변경"};
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imageView :
                        new AlertDialog.Builder(UserProfile.this)
                            .setTitle("프로필")
                            .setItems(profiles, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (profiles[which]) {
                                        case "사진촬영":
                                            Toast.makeText(UserProfile.this, "사진촬영", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "앨범에서 사진 선택":
                                            Toast.makeText(UserProfile.this, "앨범에서 사진 선택", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "기본 이미지로 변경":
                                            Toast.makeText(UserProfile.this, "기본 이미지로 변경", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                }
                            })
                            .show();
                        break;
                }
            }
        });

        textViewLv.setText("60%");
        textViewLk.setText("54 위");

        String lv = (String) intent.getExtras().get("userLv");
        String name = (String) intent.getExtras().get("userName");
        String style = (String) intent.getExtras().get("userStyle");

        imageView.setImageResource(intent.getIntExtra("image", 0));
        userLv.setText(lv);
        userName.setText(name);
        userStyle.setText(style);

        tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new ProfileStatus(), "속성");
        adapter.AddFragment(new ProfileStyle(), "호칭");
        adapter.AddFragment(new ProfileMap(), "정복지도");
        adapter.AddFragment(new ProfileFollow(), "팔로우");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);

        imageView.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT >= 21) {
            imageView.setClipToOutline(true);
        }


    }
}
