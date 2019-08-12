package com.demo.recyclerviewsyncdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class KeyValueAdapter extends RecyclerView.Adapter<KeyValueAdapter.MyViewHolder> {

    private ArrayList<KeyValue> keyValues;

    KeyValueAdapter(ArrayList<KeyValue> keyValues) {
        this.keyValues = keyValues;
    }

    @Override
    public KeyValueAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.key_value_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeyValueAdapter.MyViewHolder holder, int position) {
        holder.key.setText(keyValues.get(position).getKey());
        holder.value.setText(keyValues.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return keyValues.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView key, value;

        MyViewHolder(View itemView) {
            super(itemView);

            key = itemView.findViewById(R.id.key);
            value = itemView.findViewById(R.id.value);
        }

    }
}