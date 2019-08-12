package com.demo.recyclerviewsyncdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AttributesAdapter extends RecyclerView.Adapter<AttributesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Attribute> attributes;

    AttributesAdapter(Context context, ArrayList<Attribute> attributes) {
        this.context = context;
        this.attributes = attributes;
    }

    @NonNull
    @Override
    public AttributesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attribute_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttributesAdapter.MyViewHolder holder, int position) {
        holder.recyclerViewAttributes.setAdapter(new KeyValueAdapter(attributes.get(position).getAttributes()));
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerViewAttributes;

        MyViewHolder(View itemView) {
            super(itemView);

            recyclerViewAttributes = itemView.findViewById(R.id.rv_attributes);
            recyclerViewAttributes.setLayoutManager(new LinearLayoutManager(context));
        }

    }
}