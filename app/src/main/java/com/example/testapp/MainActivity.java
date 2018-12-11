package com.example.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        JSONArray result = null;
        List<CatData> catDataList = null;

        NetworkTask networkTask = new NetworkTask();

        try {
            result = networkTask.execute().get();
            catDataList = makeList(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(catDataList);
        recyclerView.setAdapter(recyclerAdapter);

    }


    public List<CatData> makeList(JSONArray input) throws JSONException, ExecutionException, InterruptedException {
        List<CatData> result = new ArrayList<>();
        for(int i = 0; i < input.length(); i++) {
            CatData catdata = new CatData(input.getJSONObject(i));
            result.add(catdata);
        }
        return result;
    }

}
