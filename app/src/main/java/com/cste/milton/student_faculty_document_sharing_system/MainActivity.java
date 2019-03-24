package com.cste.milton.student_faculty_document_sharing_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    private static final int Splash_time_out = 2000;
    private static ImageView image_cste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home_intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(home_intent);
            }
        },Splash_time_out);
        image_cste = (ImageView)findViewById(R.id.image_cste);
        image_cste.setImageResource(R.drawable.cste);
    }

}
