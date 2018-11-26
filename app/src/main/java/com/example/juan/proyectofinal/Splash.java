package com.example.juan.proyectofinal;

import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawableLoadProvider;
import com.master.glideimageview.GlideImageView;


public class Splash extends AppCompatActivity {

    GlideImageView glidehp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        glidehp = findViewById(R.id.idglide);
        glidehp.loadImageUrl("https://media.giphy.com/media/131tNuGktpXGhy/giphy.gif");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(Splash.this, MainActivity.class);
                startActivity(in);
            }
        },4000);
    }



}
