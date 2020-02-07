package com.example.binder.EnterActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.binder.MainActivity;
import com.example.binder.R;

public class SplashActivity extends AppCompatActivity {
    private AnimationDrawable animationDrawableBG;
    private LinearLayout linearLayoutSplashBack;
    private static int WELCOME_TIMEOUT = 5000;
    private ImageView imgLogo;
    private TextView subTextFirst,subTextSecond, subTextThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        final Animation animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        final Animation animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        subTextFirst = findViewById(R.id.subTextFirst);
        subTextSecond = findViewById(R.id.subTextSecond);
        subTextThird = findViewById(R.id.subTextThird);
        imgLogo = findViewById(R.id.imgLog);
        imgLogo.startAnimation(animScale);
        linearLayoutSplashBack = findViewById(R.id.linearLayoutSplashBack);
        animationDrawableBG = (AnimationDrawable) linearLayoutSplashBack.getBackground();
        animationDrawableBG.setEnterFadeDuration(700);
        animationDrawableBG.setExitFadeDuration(600);
        animationDrawableBG.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                subTextFirst.startAnimation(animFadeIn);

            }
        }, 0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                subTextSecond.startAnimation(animFadeIn);

            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                subTextThird.startAnimation(animFadeIn);
            }
        }, 2500);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                subTextFirst.startAnimation(animFadeOut);
                subTextSecond.startAnimation(animFadeOut);
                subTextThird.startAnimation(animFadeOut);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        }, WELCOME_TIMEOUT);

    }
}
