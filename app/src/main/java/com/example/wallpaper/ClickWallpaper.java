package com.example.wallpaper;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.jsibbold.zoomage.ZoomageView;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ClickWallpaper extends Activity implements WallpaperAdapter.Myclick {

    String s1;
    String path;
    ImageView back, favourites, more, share, next, set, download;
    ZoomageView iv;
    ProgressBar progressbar;

    RecyclerView rv2;

    int pos = MyApp.getpos();

    ArrayList<WallpaperItem> wallpaperItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_wallpaper);

        back = findViewById(R.id.back);
        favourites = findViewById(R.id.favourites);
        share = findViewById(R.id.share);
        next = findViewById(R.id.next);
        rv2 = findViewById(R.id.rv2);
        iv = findViewById(R.id.iv);
        set = findViewById(R.id.set);
        download = findViewById(R.id.download);
        progressbar = findViewById(R.id.progressbar);

        path = MyApp.getImage();

        Glide.with(this).load(path).into(iv);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rv2.setLayoutManager(gridLayoutManager);

        WallpaperAdapter wallpaperAdapter = new WallpaperAdapter(MyApp.getMylist(), ClickWallpaper.this, ClickWallpaper.this);
        rv2.setAdapter(wallpaperAdapter);

        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = MyApp.db.rawQuery("select * from fav where wallpaper = '" + MyApp.getImage() + "'", null);

                if (cursor != null) {
                    if (cursor.moveToNext()) {
                        favourites.setImageResource(R.drawable.favourite);
                        MyApp.db.execSQL("delete from fav where wallpaper = '" + MyApp.getImage() + "'");
                    } else {
                        favourites.setImageResource(R.drawable.likee);
                        MyApp.db.execSQL("insert into fav(wallpaper) values('" + MyApp.getImage() + "')");
                    }
                }
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String path = MyApp.getImage();

                downloadAnyFile(path, "d");

            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = MyApp.getImage();

                downloadAnyFile(path, "set");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    pos--;
                    String path = MyApp.getMylist().get(pos).getPath();
                    MyApp.setImage(path);
                    Glide.with(ClickWallpaper.this).load(MyApp.getImage()).into(iv);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    pos++;
                    String path = MyApp.getMylist().get(pos).getPath();
                    MyApp.setImage(path);
                    Glide.with(ClickWallpaper.this).load(MyApp.getImage()).into(iv);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

//        path=getIntent().getExtras().getString("image");

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadAnyFile(MyApp.getImage(), "share");
            }
        });

    }

    private void downloadAnyFile(String url, String type) {

        progressbar.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Started downloading....", Toast.LENGTH_SHORT).show();
        String fileName = getFileNameFromURL(url);
        String FolderName = getString(R.string.app_name);
        File dir = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dir = new File(getExternalFilesDir(null) + "/" + FolderName);
        } else {
            dir = new File(getFilesDir() + "/" + FolderName);
        }

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String path = dir.getAbsolutePath() + "/" + fileName;

        File file = new File(path);

        if (file.exists()) {
            progressbar.setVisibility(View.GONE);
            if (type.equals("d")) {
                Toast.makeText(ClickWallpaper.this, "Already downloaded", Toast.LENGTH_SHORT).show();
            }
            if (type.equals("set")) {

                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(getApplicationContext());
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);
                try {
                    myWallpaperManager.setBitmap(bitmap);
                    Toast.makeText(ClickWallpaper.this, "Wallpaper set successfully : ", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (type.equals("share")) {

                Uri uriToImage = FileProvider.getUriForFile(ClickWallpaper.this, BuildConfig.APPLICATION_ID + ".provider", new File(path));
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, null));
//                Toast.makeText(ClickWallpaper.this, "Successfully share", Toast.LENGTH_SHORT).show();

            }

            return;
        }


        int downloadId = PRDownloader.download(url, dir.getAbsolutePath(), fileName)
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {

                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {

                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        progressbar.setVisibility(View.GONE);

                        if (type.equals("d")) {
                            Toast.makeText(ClickWallpaper.this, "Successfully downloaded", Toast.LENGTH_SHORT).show();

                        }
                        if (type.equals("set")) {

                            WallpaperManager myWallpaperManager
                                    = WallpaperManager.getInstance(getApplicationContext());
                            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                            Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);
                            try {
                                myWallpaperManager.setBitmap(bitmap);
                                Toast.makeText(ClickWallpaper.this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        if (type.equals("share")) {

                            Uri uriToImage = FileProvider.getUriForFile(ClickWallpaper.this, BuildConfig.APPLICATION_ID + ".provider", new File(path));
                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
                            shareIntent.setType("image/*");
                            startActivity(Intent.createChooser(shareIntent, null));
//                            Toast.makeText(ClickWallpaper.this, "Successfully share", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Error error) {

                    }

                });
    }

    @Override
    public void getmypos(int pos) {

        String path = MyApp.getMylist().get(pos).getPath();

        MyApp.setImage(path);

        Glide.with(this).load(MyApp.getImage()).into(iv);

        dataload();

    }

    public void dataload() {
        Cursor cursor = MyApp.db.rawQuery("select * from fav where wallpaper = '" + MyApp.getImage() + "'", null);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                favourites.setImageResource(R.drawable.likee);
            } else {
                favourites.setImageResource(R.drawable.like);
            }
        }
    }

    public static String getFileNameFromURL(String url) {
        if (url == null) {
            return "";
        }
        try {
            URL resource = new URL(url);
            String host = resource.getHost();
            if (host.length() > 0 && url.endsWith(host)) {
                // handle ...example.com
                return "";
            }
        } catch (MalformedURLException e) {
            return "";
        }

        int startIndex = url.lastIndexOf('/') + 1;
        int length = url.length();

        // find end index for ?
        int lastQMPos = url.lastIndexOf('?');
        if (lastQMPos == -1) {
            lastQMPos = length;
        }

        // find end index for #
        int lastHashPos = url.lastIndexOf('#');
        if (lastHashPos == -1) {
            lastHashPos = length;
        }

        // calculate the end index
        int endIndex = Math.min(lastQMPos, lastHashPos);
        return url.substring(startIndex, endIndex);
    }

    private void downManager(String durl) {

        Toast.makeText(ClickWallpaper.this, "Download Start look inside notification.....", Toast.LENGTH_SHORT).show();
        final String strFileName = getFileNameFromURL(durl);

        Uri source = Uri.parse(durl);
        // Make a new request pointing to the .apk url
        DownloadManager.Request request = new DownloadManager.Request(source);
        // appears the same in Notification bar while downloading
        request.setDescription("Download Start......");
        request.setTitle(strFileName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
//         save the file in the "Downloads" folder of SDCARD
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, strFileName);
        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

        Toast.makeText(ClickWallpaper.this, "Go to Download Folder", Toast.LENGTH_SHORT).show();

    }

}
