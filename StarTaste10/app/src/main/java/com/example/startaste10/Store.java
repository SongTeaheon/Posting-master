package com.example.startaste10;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class Store extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        TabLayout tablayout;
        ViewPager viewPager;

        tablayout = (TabLayout) findViewById(R.id.tablayout_store);
        viewPager = (ViewPager) findViewById(R.id.viewpager_store);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new StoreFacility(), "시설");
        adapter.AddFragment(new StoreTheme(), "테마");
        adapter.AddFragment(new StoreEnv(), "환경");
        adapter.AddFragment(new StoreMana(), "마나");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }
}
