package com.angogasapps.zadanie_3_4;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String photoUrl = "https://myitschool.ru/edu/pluginfile.php/1/core_admin/logocompact/100x100/1607409728/SAMSUNG_IT_School_Logo_Full_Compact.png";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image);

        new ImageLoaderThread(MainActivity.this).execute(photoUrl);
    }
}