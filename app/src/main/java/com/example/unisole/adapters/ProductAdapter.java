package com.example.unisole.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.unisole.ProductActivity;
import com.example.unisole.ProductListActivity;
import com.example.unisole.R;
import com.example.unisole.models.Inventory;
import com.example.unisole.models.InventoryPK;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.viewHolder> {
    ArrayList<Inventory> list;
    ProductListActivity context;

    public ProductAdapter(ArrayList<Inventory> list ,ProductListActivity context){
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_product,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Inventory inventory=list.get(position);
        holder.productId=inventory.getInventoryPK().getProduct().getProductId();
        holder.productName.setText(inventory.getInventoryPK().getProduct().getProductName());
        holder.productPrice.setText(String.valueOf(inventory.getPrice()));
        Glide.with(context)
                .load(inventory.getProductImage())
                .placeholder(R.drawable.shoes)
                .into(holder.productImage);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onProductClick(holder.productId,inventory.getInventoryPK().getSize().getSizeId(),inventory.getInventoryPK().getColor().getColorId(),inventory.getInventoryPK().getMerchantId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface ProductClick{
        public void onProductClick(String productId, int sizeId, int colorId, String merchantId);
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        View rootView;
        String productId;
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        public viewHolder(@NonNull View itemView){
            super(itemView);
            rootView=itemView;
            productImage=itemView.findViewById(R.id.iv_product_list_image);
            productName=itemView.findViewById(R.id.tv_product_name_row);
            productPrice=itemView.findViewById(R.id.tv_price_row);
        }

    }
}
