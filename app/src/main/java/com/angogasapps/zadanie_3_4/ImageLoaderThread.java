package com.angogasapps.zadanie_3_4;

import android.annotation.SuppressLint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class ImageLoaderThread extends AsyncTask<String, Integer, Bitmap> {
    @SuppressLint("StaticFieldLeak")
    MainActivity activity;
    boolean success = false;

    public ImageLoaderThread(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.runOnUiThread(() -> {
            Toast.makeText(activity.getApplicationContext(), "Загрузка картинки начнётся через 4 секунды...",
                    Toast.LENGTH_LONG).show();
        });
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            InputStream inputStream = new URL(strings[0]).openStream();
            if (inputStream == null) {
                //ошибка
                Log.e("tag", "error");
            } else {
                success = true;
                return BitmapFactory.decodeStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        activity.runOnUiThread(() -> {
            Toast.makeText(activity.getApplicationContext(), "При загрузке произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
        });
        Log.e("tag", "ОШИБКА В ЗАГРУЗКЕ!");
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (success) {
            activity.runOnUiThread(() -> {
                Toast.makeText(activity.getApplicationContext(), "Загрузка картинки завершена...",
                        Toast.LENGTH_LONG).show();
                activity.imageView.setImageBitmap(bitmap);
            });
        }
    }
}
