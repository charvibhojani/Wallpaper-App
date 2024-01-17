package com.example.wallpaper;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MyApp extends Application {

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    static SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences("my", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        db = openOrCreateDatabase("my.db", MODE_PRIVATE, null);

        db.execSQL("create table if not exists fav(wallpaper text)");

    }

    public static void setpos(int pos) {
        editor.putInt("pos", pos).commit();
    }

    public static int getpos() {
        return sharedPreferences.getInt("pos", 0);
    }

    public static void setvpos(int vpos) {
        editor.putInt("vpos", vpos).commit();
    }

    public static int getvpos() {
        return sharedPreferences.getInt("vpos", 0);
    }

    public static void setCategory(String id) {
        editor.putString("id", id).commit();
    }

    public static String getCategory() {
        return sharedPreferences.getString("id", "");
    }

    public static void setImage(String img) {
        editor.putString("img", img).commit();
    }

    public static String getImage() {
        return sharedPreferences.getString("img", "");
    }

    public static void setMylist(ArrayList<WallpaperItem> wallpaperItems) {

        Gson gson = new Gson();
        String arrayData = gson.toJson(wallpaperItems);

        editor.putString("list", arrayData).commit();

    }

    public static ArrayList<WallpaperItem> getMylist() {

        String data = sharedPreferences.getString("list", "");

        Gson gson = new Gson();

        ArrayList<WallpaperItem> wallpaperlist = (ArrayList<WallpaperItem>) gson.fromJson(data,
                new TypeToken<ArrayList<WallpaperItem>>() {

                }.getType());

        return wallpaperlist;

    }

}