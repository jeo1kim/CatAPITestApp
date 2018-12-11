package com.example.testapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecViewHolder> {

    List<CatData> data;

    public RecyclerAdapter(List<CatData> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        RecViewHolder holder = new RecViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecViewHolder viewHolder, int i) {

        try {
            viewHolder.imageView.setImageBitmap(data.get(i).getImageBitMap());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
