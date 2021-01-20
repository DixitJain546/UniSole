package com.example.unisole.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.unisole.R;
import com.example.unisole.SearchActivity;
import com.example.unisole.models.Search;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    ArrayList<Search> list;
    SearchActivity context;

    public SearchAdapter(ArrayList<Search> list ,SearchActivity context){
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Search inventory=list.get(position);
        holder.productName.setText(inventory.getName());
        holder.productPrice.setText(String.valueOf(inventory.getPrice()));
        Glide.with(context)
                .load(inventory.getImage())
                .placeholder(R.drawable.shoes)
                .into(holder.productImage);
        holder.productId=String.valueOf(inventory.getId());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onSearchClick(holder.productId,inventory.getSizeId(),inventory.getColorId(),inventory.getMerchantId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface SearchClick{
        public void onSearchClick(String productId, int sizeId, int colorId, String merchantId);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        View rootView;
        String productId;
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            rootView=itemView;
            productImage=itemView.findViewById(R.id.ijkl);
            productName=itemView.findViewById(R.id.abcd);
            productPrice=itemView.findViewById(R.id.efgh);
        }


    }
}
