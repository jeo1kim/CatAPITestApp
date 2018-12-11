package com.example.testapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkTask extends AsyncTask<String, Void, JSONArray> {

//    curl --location --request GET "https://api.thecatapi.com/v1/images/search?format=json" \
//            --header "Content-Type: application/json" \
//            --header "x-api-key: DEMO-API-KEY"

    final String API_KEY ="9037af91-05b7-488f-8bef-2677d56558bf";
    @Override
    protected JSONArray doInBackground(String... strings) {
        HttpURLConnection connection = null;
        String inputLine = null;
        JSONArray result = null;
        try {
            URL url = new URL("https://api.thecatapi.com/v1/images/search?format=json&limit=15");

            connection = (HttpURLConnection) url.openConnection();
            //System.out.println("connection "+connection.getResponseMessage());
            connection.setRequestMethod("GET");
            //connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("x-api-key", API_KEY);
            connection.getResponseCode();

            //Log.v("responsecode", connection.getResponseCode() );
            InputStream in = connection.getInputStream();

            Log.v("inputtream", in.toString());
            InputStreamReader reader = new InputStreamReader(in);

            BufferedReader bufferedReaderreader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();

            while((inputLine = bufferedReaderreader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            bufferedReaderreader.close();
            //Set our result equal to our stringBuilder
            result = new JSONArray(stringBuilder.toString());

            Log.v("APICALL", result.toString());


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }


        return result;
    }


}
