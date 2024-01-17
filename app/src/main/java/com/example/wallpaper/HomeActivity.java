package com.example.wallpaper;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wallpaper.databinding.ListCategoryBinding;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.Myclick {

    ListCategoryBinding listCategoryBinding;

    String image = "";
    ArrayList<HomeItem> homeItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listCategoryBinding = ListCategoryBinding.inflate(getLayoutInflater());
        setContentView(listCategoryBinding.getRoot());

        registerNetworkBroadcastForNougat();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listCategoryBinding.lv.setLayoutManager(linearLayoutManager);

        homeItems.add(new HomeItem("https://i.pinimg.com/564x/d6/dd/2e/d6dd2e4ba26aec516dc92dadb36df99f.jpg", "Sun"));
        homeItems.add(new HomeItem("https://i.pinimg.com/564x/02/2a/53/022a53117588e268d2378ed76f4272f1.jpg", "Moon"));
        homeItems.add(new HomeItem("https://i.pinimg.com/564x/77/18/cf/7718cfe5d0d9d612b01c071aef80ee84.jpg", "Bikes"));
        homeItems.add(new HomeItem("https://i.pinimg.com/564x/d8/f5/74/d8f574b80774d2a99fb674cc91f23c78.jpg", "Cars"));
        homeItems.add(new HomeItem("https://i.pinimg.com/564x/a4/bf/39/a4bf39fb9bb01494ab3a6e99030828ca.jpg", "Animals"));
        homeItems.add(new HomeItem("https://i.pinimg.com/564x/6e/91/59/6e9159a1d47c5759b2820ace1d5b9ada.jpg", "Birds"));

        HomeAdapter homeAdapter = new HomeAdapter(homeItems, HomeActivity.this, HomeActivity.this);
        listCategoryBinding.lv.setAdapter(homeAdapter);

        listCategoryBinding.favv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Favourites.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void registerNetworkBroadcastForNougat() {
        registerReceiver(new NetCheckReceiver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");

            if (message.equalsIgnoreCase("on")) {

                listCategoryBinding.data.setVisibility(View.VISIBLE);
                listCategoryBinding.nodata.setVisibility(View.GONE);
            } else {
                listCategoryBinding.data.setVisibility(View.GONE);
                listCategoryBinding.nodata.setVisibility(View.VISIBLE);
            }
        }
    };

    public void getmypos(int pos) {
        String name = homeItems.get(pos).getName();
//        Toast.makeText(HomeActivity.this, name, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeActivity.this, WallpaperActivity.class);

//        intent.putExtra("title", name);

        MyApp.setCategory(name);

        startActivity(intent);
    }
}