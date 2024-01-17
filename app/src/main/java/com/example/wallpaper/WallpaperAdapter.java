package com.example.wallpaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.Myh> {

    ArrayList<WallpaperItem> wallpaperItems;
    Context context;
    Myclick myclick;

    public WallpaperAdapter(ArrayList<WallpaperItem> wallpaperItems, Context context, Myclick myclick) {

        this.wallpaperItems = wallpaperItems;
        this.context = context;
        this.myclick = myclick;
    }

    @NonNull
    @Override
    public WallpaperAdapter.Myh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_wallpaper, parent, false);

        return new Myh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperAdapter.Myh holder, int position) {

        Glide.with(context).load(wallpaperItems.get(position).getPath()).into(holder.wallpaper);

        holder.lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myclick.getmypos(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return wallpaperItems.size();
    }

    public class Myh extends RecyclerView.ViewHolder {
        LinearLayout lv;
        ImageView wallpaper;

        public Myh(@NonNull View itemView) {
            super(itemView);

            wallpaper = itemView.findViewById(R.id.wallpaper);
            lv = itemView.findViewById(R.id.lv);
        }
    }

    public interface Myclick {

        public void getmypos(int pos);

    }
}
