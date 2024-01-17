package com.example.wallpaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class MoreOptions extends Activity {

    Button home, lock, like;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_options);

//        home = findViewById(R.id.home);
//        lock = findViewById(R.id.lock);
        like = findViewById(R.id.like);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreOptions.this, Favourites.class);
                startActivity(intent);
            }
        });

    }

}
