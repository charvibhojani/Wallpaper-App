package com.example.wallpaper;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerMain extends AppCompatActivity {

    ViewPager vp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_main);
        vp = findViewById(R.id.vp);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(MyApp.getMylist(), ViewPagerMain.this);
        vp.setAdapter(viewPagerAdapter);

        vp.setCurrentItem(MyApp.getvpos());
    }
}
