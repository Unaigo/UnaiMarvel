package com.marvel.unai.unaimarvel.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marvel.unai.unaimarvel.Models.AppModels.ItemList;
import com.marvel.unai.unaimarvel.R;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder> {

    private List<ItemList> itemLists;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView image;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            image = (ImageView) view.findViewById(R.id.imageView);
        }
    }


    public ItemListAdapter(List<ItemList> itemLists,Context context) {
        this.itemLists = itemLists;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ItemList itemList = itemLists.get(position);
        if(itemList.getTitle()!=null)
        holder.title.setText(itemList.getTitle());
        if(itemList.getDescription()!=null)
        holder.description.setText(itemList.getDescription());

        Glide.with(context).load(itemList.getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return itemLists.size();
    }
}