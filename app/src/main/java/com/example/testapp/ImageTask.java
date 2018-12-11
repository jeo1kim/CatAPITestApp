package com.example.testapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageTask extends AsyncTask<String, Void, Bitmap> {


    @Override
    protected Bitmap doInBackground(String... strings) {

        HttpURLConnection connection = null;
        Bitmap bitmap = null;
        InputStream reader = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();

            reader = connection.getInputStream();

            bitmap = BitmapFactory.decodeStream(reader);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bitmap;
    }
}
