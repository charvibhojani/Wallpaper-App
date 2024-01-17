package com.example.wallpaper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Favourites extends Activity implements WallpaperAdapter.Myclick {

    RecyclerView rv;
    ImageView delete;
    WallpaperAdapter wallpaperAdapter;

    ArrayList<WallpaperItem> wallpaperItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_fav);

        rv = findViewById(R.id.rv);
        delete = findViewById(R.id.delete);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);

        dataload();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (wallpaperItems.size() > 0) {
                    wallpaperItems.clear();
                    rv.setAdapter(wallpaperAdapter);
                    Toast.makeText(Favourites.this, "Deleted all", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Favourites.this, "There is no wallpaper to delete", Toast.LENGTH_LONG).show();
                }
            }
        });

//        s1 = getIntent().getExtras().getString("title");

//        click.setText(s1);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        dataload();
    }

    public void dataload() {

        Cursor cursor = MyApp.db.rawQuery("select * from fav", null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String wallpaper = cursor.getString(0);

                wallpaperItems.add(mycon(wallpaper));

            }

            WallpaperAdapter wallpaperAdapter = new WallpaperAdapter(wallpaperItems, Favourites.this, Favourites.this);
            rv.setAdapter(wallpaperAdapter);
        }

    }

    public WallpaperItem mycon(String wallpaper) {

        WallpaperItem wallpaperItem = null;


        Cursor cursor = MyApp.db.rawQuery("select * from fav where wallpaper='" + wallpaper + "'", null);
        if (cursor != null) {

            if (cursor.moveToNext()) {

                String wallpapers = cursor.getString(0);

                Log.d("TAG", "dataload: " + wallpapers);

                wallpaperItem = new WallpaperItem(wallpaper);

            }

        }

        return wallpaperItem;
    }

    @Override
    public void getmypos(int pos) {

    }
}
