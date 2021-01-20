package com.example.unisole.adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unisole.HomeActivity;
import com.example.unisole.ProductListActivity;
import com.example.unisole.R;


import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewHolder> {
    ArrayList<String> list;
    HomeActivity context;
    private static final String CATEGORY_ID="categoryId";

    public HomeAdapter(ArrayList<String> list, HomeActivity context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_category,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String categoryName=list.get(position);
        holder.textView.setText(categoryName);
        switch (categoryName){
            case "Boots": {
                holder.categoryId="c1";
                holder.imageView.setImageResource(R.drawable.boots);
                break;
            }
            case "Heels": {
                holder.categoryId="c2";
                holder.imageView.setImageResource(R.drawable.heels);
                break;
            }
            case "Shoes": {
                holder.categoryId="c3";
                holder.imageView.setImageResource(R.drawable.shoes);
                break;
            }
            case "Sneakers": {
                holder.categoryId="c4";
                holder.imageView.setImageResource(R.drawable.sneakers);
                break;
            }
            case "Sandals": {
                holder.categoryId="c5";
                holder.imageView.setImageResource(R.drawable.sandals);
                break;
            }
        }
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProductListActivity.class);
                intent.putExtra(CATEGORY_ID,holder.categoryId);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{
        String categoryId;
        ImageView imageView;
        TextView textView ;
        View rootView;
        public viewHolder(@NonNull View itemView){
            super(itemView);
            rootView=itemView;
            imageView=itemView.findViewById(R.id.iv_category_image);
            textView = itemView.findViewById(R.id.tv_category_name);
        }

    }
}
