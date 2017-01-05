package com.example.aum.smartyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by aum on 11/12/2016.
 */
public class Splash extends Activity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ImageView image = (ImageView)findViewById(R.id.splashhat);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.hat_animation);
        image.startAnimation(animation1);

        new Handler().postDelayed(new Runnable(){
        @Override
        public void run() {
            // This method will be executed once the timer is over
            // Start your app main activity
            Intent i = new Intent(Splash.this, MainActivity.class);
            startActivity(i);

            // close this activity
            finish();
        }
    }, SPLASH_TIME_OUT);
    }
}
