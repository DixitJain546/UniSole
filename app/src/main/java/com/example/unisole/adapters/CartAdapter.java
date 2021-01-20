package com.example.unisole.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unisole.CartActivity;
import com.example.unisole.R;

import com.example.unisole.models.CartItemData;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHolder> {
    ArrayList<CartItemData> list = new ArrayList<>();
    CartActivity context;

    public CartAdapter(ArrayList<CartItemData> list, CartActivity context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_cart_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CartItemData cartItem = list.get(position);
        holder.productPrice.setText(String.valueOf(cartItem.getCartItem().getPrice()));
        holder.productName.setText(cartItem.getProduct().getProductName());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onCartClick(cartItem.getCartItem().getCartItemId(),cartItem);
            }
        });


    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    public interface CartClick {
        public void onCartClick(String cartItemId, CartItemData cartItemData);
    }

        public class viewHolder extends RecyclerView.ViewHolder {
        View rootView;
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        Button remove;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            rootView=itemView;
            productName = itemView.findViewById(R.id.tv_name_cart);
            productPrice=itemView.findViewById(R.id.tv_price_cart);
            remove=itemView.findViewById(R.id.btn_remove_cart);
        }
    }

}
