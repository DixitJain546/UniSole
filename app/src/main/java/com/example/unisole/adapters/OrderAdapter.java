package com.example.unisole.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.RatingBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unisole.OrderActivity;
import com.example.unisole.R;
import com.example.unisole.models.OrderItemData;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewHolder> {
    ArrayList<OrderItemData> list = new ArrayList<>();
    OrderActivity context;

    public OrderAdapter(ArrayList<OrderItemData> list, OrderActivity context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_order_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        OrderItemData cartItem = list.get(position);
        holder.productPrice.setText(String.valueOf(cartItem.getOrderItem().getPrice()));
        holder.productName.setText(cartItem.getProduct().getProductName());
        holder.merchant.setText(cartItem.getMerchantStore().getMerchantStoreName());
        holder.ratingBar.setRating((float) cartItem.getOrderItem().getRating());
//        holder.ratingBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.onOrderClick(cartItem.getOrderItem().getOrderItemId(),cartItem);
//            }
//        });
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                context.onOrderClick(cartItem.getOrderItem().getOrderItemId(),(double) rating);

            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    public interface OrderClick {
        public void onOrderClick(String orderItemId, double rating);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView productName;
        TextView productPrice;
        TextView merchant;
        RatingBar ratingBar;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            rootView=itemView;
            ratingBar=itemView.findViewById(R.id.rb_order);
            productName = itemView.findViewById(R.id.tv_name_order_item);
            productPrice=itemView.findViewById(R.id.tv_price_order_item);
            merchant=itemView.findViewById(R.id.tv_date_order_item);


        }
    }

}

