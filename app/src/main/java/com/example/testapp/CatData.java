package com.example.testapp;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class CatData {

    private String id;
    private String imageURL;
    private Bitmap imageBitMap;


    public CatData(JSONObject data) throws JSONException, ExecutionException, InterruptedException {
        this.id = data.getString("id");
        this.imageURL = data.getString("url");
        callImageTask();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Bitmap getImageBitMap() throws ExecutionException, InterruptedException {

        return imageBitMap;
    }

    public void setImageBitMap(Bitmap imageBitMap) {
        this.imageBitMap = imageBitMap;
    }

    private void callImageTask() throws ExecutionException, InterruptedException {

        ImageTask imageTask = new ImageTask();
        imageBitMap = imageTask.execute(imageURL).get();
    }
}
