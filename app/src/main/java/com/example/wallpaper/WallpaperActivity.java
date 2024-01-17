package com.example.wallpaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class WallpaperActivity extends Activity implements WallpaperAdapter.Myclick {

    ImageView back, iv;
    TextView name;
    RecyclerView lv;
    String cat = "";

//    String path1 = "https://image.pngaaa.com/467/306467-middle.png";

    ArrayList<WallpaperItem> wallpaperItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        lv = findViewById(R.id.lv);
        name = findViewById(R.id.name);
        back = findViewById(R.id.back);

//        cat = getIntent().getExtras().getString("title");

        cat = MyApp.getCategory();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        lv.setLayoutManager(gridLayoutManager);

        if (cat.equalsIgnoreCase("Sun")) {
            wallpaperItems.add(new WallpaperItem("https://c0.wallpaperflare.com/preview/991/404/875/sunset-shadow-shadow-man-evening.jpg"));
            wallpaperItems.add(new WallpaperItem("https://graffitiwallpaper.com/pics/listings/372_portrait.jpg"));
            wallpaperItems.add(new WallpaperItem("https://w0.peakpx.com/wallpaper/319/475/HD-wallpaper-bright-sun-bright-sun-sunlight-nature.jpg"));
            wallpaperItems.add(new WallpaperItem("https://r1.ilikewallpaper.net/iphone-11-pro-wallpapers/download-118762/Sun-4K-Resolution-HD-Nature-4K-Images-Photos-and-B.jpg"));
            wallpaperItems.add(new WallpaperItem("https://wallpaperaccess.com/full/526070.jpg"));
            wallpaperItems.add(new WallpaperItem("https://wallpaperaccess.com/full/192672.jpg"));
            wallpaperItems.add(new WallpaperItem("https://wallpapercave.com/wp/wp7711561.jpg"));
            wallpaperItems.add(new WallpaperItem("https://wallpaperboat.com/wp-content/uploads/2020/07/13/50067/sun-16.jpg"));
            wallpaperItems.add(new WallpaperItem("https://img5.goodfon.com/wallpaper/nbig/2/19/sun-flowers-morning.jpg"));
            wallpaperItems.add(new WallpaperItem("https://c4.wallpaperflare.com/wallpaper/587/102/418/arial-photography-of-roadway-between-green-leaf-trees-wallpaper-preview.jpg"));
        }

        if (cat.equalsIgnoreCase("Moon")) {
            wallpaperItems.add(new WallpaperItem("https://mobwallpapershd.com/wp-content/uploads/2022/07/Moon-Wallpaper.jpg"));
            wallpaperItems.add(new WallpaperItem("https://wallpaperaccess.com/full/11562.jpg"));
            wallpaperItems.add(new WallpaperItem("https://w0.peakpx.com/wallpaper/756/283/HD-wallpaper-beautiful-sky-light-sitare-moon-thumbnail.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/736x/71/73/e5/7173e51cfa7ec9a452e9beeb250a1ef5.jpg"));
            wallpaperItems.add(new WallpaperItem("https://my4kwallpapers.com/wp-content/uploads/2022/03/4k-Moon-Wallpaper.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/ab/f0/0c/abf00cbd30fa18d47bef7a735e556338.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/ed/14/7e/ed147eceff719574d783810ffe525b1d.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/3a/9d/54/3a9d549d2409f52509b26742431a90d9.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/ad/66/21/ad6621d58246ea621117d4b8aa99df5b.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/b6/ea/5e/b6ea5e1ac56a6421b639461b37015af4.jpg"));
        }
        if (cat.equalsIgnoreCase("Bikes")) {
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/ee/1d/c7/ee1dc7be83303392cd559582c15a3ad4.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/00/5a/1f/005a1f5ca301cd8fb8153a49ea2c6ff4.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/bd/d2/cc/bdd2cc9ddb07f9459d8f9dc47ffb63a6.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/57/89/73/5789736536d1f36b2e03532c73cf9425.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/5d/c0/8b/5dc08b1edaf440e415761ba994d72c72.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/ec/5e/97/ec5e970bc89e45944115dcee2a3eb851.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/0a/51/c6/0a51c62e7db462308a02756824e71087.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/6f/20/34/6f20348414f2af49034964c758ebdde9.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/b0/c3/36/b0c336514c1c778118a8ca7c7262cb2c.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/0a/fb/de/0afbde6348f0fd854b479ee1d9ce7b18.jpg"));

        }
        if (cat.equalsIgnoreCase("Cars")) {
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/74/9e/38/749e388db734c89979682bb0e4a6314b.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/7e/b4/3f/7eb43f370b031a1fed9fd4fd7134c23a.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/ad/ea/a1/adeaa1d253aa4b4f103f88aec77bb0eb.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/03/b8/47/03b8476dc32bba390fbb04917636e3d6.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/a7/c3/5e/a7c35e57316fbf0c3c4b0da5dbf34a48.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/4c/36/cf/4c36cffe28850f9effb4c1d05f351b41.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/86/18/63/861863e834c60da4f2a506b0e6d416f0.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/a8/df/af/a8dfaf969aa5a42af2ee5a293f2ebe93.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/57/b4/5b/57b45be7749672cd3214ffb3a5c9573e.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/bd/db/f1/bddbf1aeb8b4956345b419803229429e.jpg"));
        }
        if (cat.equalsIgnoreCase("Animals")) {
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/b2/da/e2/b2dae2c625777f4db1362be5067b6e5f.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/da/19/0e/da190ec40c70ab16d38e513aac0656c1.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/29/cb/9d/29cb9d314138200b51ed4b9e08738ef7.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/05/68/62/0568623907622933bbac240fc906ce06.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/95/20/9a/95209a0581b770a11bef1d6aeeb658e0.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/7b/9e/bd/7b9ebd5643a04ebf833d5afb625cfbc7.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/67/5d/b6/675db601f9bca8d80a567e6504cc2bd4.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/b8/d9/07/b8d9072a3a45ccda26fdb87a05fa2d34.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/bf/8c/07/bf8c077684112ec4bf06171377e8a1b5.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/af/04/fa/af04fad65ea06a814c8668c373b94348.jpg"));
        }
        if (cat.equalsIgnoreCase("Birds")) {
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/ec/26/c2/ec26c26efaf28316404516d5680fddfc.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/3a/3d/74/3a3d74bc81d208fc69e956dcc251ee08.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/8f/eb/b9/8febb9bffaa2d13dd2d85f70f5c11ce1.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/75/1c/80/751c80f1d3ae6b09ca96c8f553a288a4.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/62/93/33/629333f719eb454c910b67eddc86b20d.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/aa/5b/4b/aa5b4bbc7849af3d79029ffbba14ccbd.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/0f/5b/43/0f5b438d20be497b5566e7a431662cd3.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/56/c7/1c/56c71c4eb87d4345930205878b6dceb1.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/00/e5/6e/00e56e144e9cafb1562e85cbb947e406.jpg"));
            wallpaperItems.add(new WallpaperItem("https://i.pinimg.com/564x/35/10/fa/3510faea8bdea209bfa0d8a7ed67a953.jpg"));
        }

        name.setText(cat);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WallpaperActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        WallpaperAdapter wallpaperAdapter = new WallpaperAdapter(wallpaperItems, WallpaperActivity.this, WallpaperActivity.this);
        lv.setAdapter(wallpaperAdapter);
    }

    @Override
    public void getmypos(int pos) {

        cat = wallpaperItems.get(pos).getPath();
        MyApp.setImage(cat);
        MyApp.setMylist(wallpaperItems);
        MyApp.setvpos(pos);
        Intent intent = new Intent(WallpaperActivity.this, ViewPagerMain.class);
        startActivity(intent);

//        Intent intent = new Intent(WallpaperActivity.this, ViewPagerMain.class);

//        intent.putExtra("image", cat);

    }
}
