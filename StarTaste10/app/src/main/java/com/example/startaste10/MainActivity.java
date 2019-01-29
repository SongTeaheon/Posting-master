package com.example.startaste10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonFinding = (Button) findViewById(R.id.buttonFinding);
        Button toGame = (Button) findViewById(R.id.toGame);
        TextView userLv = (TextView) findViewById(R.id.userLevel);
        TextView userName = (TextView) findViewById(R.id.userName);
        TextView userStyle = (TextView) findViewById(R.id.userStyle);
        ImageButton buttonUserImage = (ImageButton) findViewById(R.id.userImage);
        @SuppressLint("CutPasteId") final ImageView imageView = (ImageView) findViewById(R.id.userImage);

        userLv.setText("Lv 54");
        userName.setText("StarShape");
        userStyle.setText("와카레마시따");

        final String lv = userLv.getText().toString();
        final String name = userName.getText().toString();
        final String style = userStyle.getText().toString();

        buttonFinding.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindTaste.class);
                startActivity(intent);
            }
        });

        buttonUserImage.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                intent.putExtra("image", R.mipmap.zelda);
                intent.putExtra("userLv", lv);
                intent.putExtra("userName", name);
                intent.putExtra("userStyle", style);
                startActivity(intent);
            }
        });

        toGame.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameMain.class);
                startActivity(intent);
                finish();
            }
        });

        imageView.setBackground(new ShapeDrawable(new OvalShape()));
        imageView.setClipToOutline(true);
    }

    private long time= 0;
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-time>=2000){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 종료합니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<2000){
            finish();
        }
    }

}
