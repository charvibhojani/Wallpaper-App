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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Myh> {

    ArrayList<HomeItem> homeItems;
    Context context;
    Myclick myclick;

    public HomeAdapter(ArrayList<HomeItem> homeItems, Context context, Myclick myclick) {

        this.homeItems = homeItems;
        this.context = context;
        this.myclick = myclick;
    }

    @NonNull
    @Override
    public HomeAdapter.Myh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_home, parent, false);

        return new Myh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.Myh holder, int position) {

        holder.tv.setText(homeItems.get(position).getName());

        Glide.with(context).load(homeItems.get(position).getPath()).into(holder.iv);

        holder.ly.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                myclick.getmypos(position);
            }

        });
    }

    @Override
    public int getItemCount() {
        return homeItems.size();
    }

    public class Myh extends RecyclerView.ViewHolder {

        TextView tv;
        LinearLayout ly;
        ImageView iv;

        public Myh(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv);
            ly = itemView.findViewById(R.id.ly);
            iv = itemView.findViewById(R.id.iv);
        }
    }

    public interface Myclick {

        public void getmypos(int pos);
    }
}
