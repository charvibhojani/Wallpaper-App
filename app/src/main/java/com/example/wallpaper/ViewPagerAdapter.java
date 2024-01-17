package com.example.wallpaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {


    ArrayList<WallpaperItem> wallpaperItems;
    Context context;

    public ViewPagerAdapter(ArrayList<WallpaperItem> wallpaperItems, Context context) {
        this.wallpaperItems = wallpaperItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return wallpaperItems.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.viewpager_item,
                container, false);

        ImageView iv = layout.findViewById(R.id.iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyApp.setImage(wallpaperItems.get(position).getPath());
                MyApp.setMylist(wallpaperItems);
                MyApp.setpos(position);
                Intent intent = new Intent(context, ClickWallpaper.class);
                context.startActivity(intent);

            }
        });

        Glide.with(context).load(wallpaperItems.get(position).getPath()).into(iv);
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
