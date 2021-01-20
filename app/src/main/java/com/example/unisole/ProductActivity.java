package com.example.unisole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.bumptech.glide.Glide;
import com.example.unisole.builder.RetrofitBuilder;

import com.example.unisole.interfaces.IProductApi;
import com.example.unisole.models.Cart;
import com.example.unisole.models.CartItem;

import com.example.unisole.models.InventoryMerchantStore;
import com.example.unisole.models.Order;
import com.example.unisole.models.OrderItem;
import com.example.unisole.models.Product;
import com.example.unisole.models.Token;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductActivity extends AppCompatActivity {
    Retrofit retrofit= RetrofitBuilder.getInstance();
    IProductApi iProductApi=retrofit.create(IProductApi.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        TextView product=findViewById(R.id.tv_product_name);
        TextView price=findViewById(R.id.tv_product_price);
        TextView qty=findViewById(R.id.sp_qty);
        TextView productDesc=findViewById(R.id.tv_product_desc);
        TextView size=findViewById(R.id.tv_size_product);
        TextView color=findViewById(R.id.tv_color_product);
        TextView merchant=findViewById(R.id.tv_mechant_product);
        ImageButton addToCart=findViewById(R.id.btn_add_cart);
        ImageButton addedCart=findViewById(R.id.btn_added_to_cart);
        ImageButton buyNow=findViewById(R.id.btn_buy_now);
        String productId=getIntent().getStringExtra("productId");
        int sizeId=getIntent().getIntExtra("sizeId",0);
        int colorId=getIntent().getIntExtra("colorId",0);
        String merchantId=getIntent().getStringExtra("merchantId");

        Call<InventoryMerchantStore> responses=iProductApi.getProductByInventoryPK(productId,sizeId,colorId,merchantId);
        Callback<InventoryMerchantStore> callback=new Callback<InventoryMerchantStore>() {
            @Override
            public void onResponse(Call<InventoryMerchantStore> call, Response<InventoryMerchantStore> response) {
                    ImageView productImage=findViewById(R.id.iv_product);

                    product.setText(response.body().getInventory().getInventoryPK().getProduct().getProductName());
                    price.setText(String.valueOf(response.body().getInventory().getPrice()));
                    productDesc.setText(response.body().getInventory().getProductDescription());
                    size.setText(String.valueOf(response.body().getInventory().getInventoryPK().getSize().getSizeNumber()));
                    color.setText(response.body().getInventory().getInventoryPK().getColor().getColorName());
                    merchant.setText(response.body().getMerchantStore().getMerchantStoreName());
                    productDesc.setText(response.body().getInventory().getProductDescription());
                Glide.with(ProductActivity.this)
                        .load(response.body().getInventory().getProductImage())
                        .placeholder(R.drawable.shoes)
                        .into(productImage);
                if(response.body().getInventory().getQuantity()<=0) {
                    qty.setText("Out Of Stock");
                    addToCart.setVisibility(View.INVISIBLE);
                    buyNow.setVisibility(View.INVISIBLE);
                }

                OrderItem orderItem=new OrderItem();
                orderItem.setColorId(response.body().getInventory().getInventoryPK().getColor().getColorId());
                orderItem.setMerchantId(response.body().getInventory().getInventoryPK().getMerchantId());
                orderItem.setPrice(response.body().getInventory().getPrice());
                orderItem.setProductId(response.body().getInventory().getInventoryPK().getProduct().getProductId());
                orderItem.setQuantity(1);
                orderItem.setSizeId(response.body().getInventory().getInventoryPK().getSize().getSizeId());
                SharedPreferences sharedPreferences=getSharedPreferences("com.example.unisole",MODE_PRIVATE);
                String token=sharedPreferences.getString("TOKEN","");
                JWT jwt=new JWT(token);
                Claim emailClaim=jwt.getClaim("email");
                String email=emailClaim.asString();
                buyNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       // Toast.makeText(ProductActivity.this,"buy now",Toast.LENGTH_SHORT).show();
                        Call<Order> responses4=iProductApi.buyNow(orderItem,email);
                        Callback<Order> callback4=new Callback<Order>() {
                            @Override
                            public void onResponse(Call<Order> call, Response<Order> response) {

                                    Toast.makeText(ProductActivity.this,"Woohooo!! Your order has been placed..",Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(Call<Order> call, Throwable t) {
                                Log.d("Success order",t.getMessage());
                                Toast.makeText(ProductActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        };
                        responses4.enqueue(callback4);
                    }
                });
                findViewById(R.id.btn_add_cart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CartItem cartItem=new CartItem();
                        cartItem.setColorId(colorId);
                        cartItem.setProductId(productId);
                        cartItem.setMerchantId(merchantId);
                        cartItem.setQuantity(1);
                        cartItem.setPrice(Integer.parseInt(price.getText().toString()));
                        cartItem.setSizeId(sizeId);
                        Log.d("Cart Item : ",cartItem.toString());




                        Call<Cart> responses3=iProductApi.getCart(email);
                        Callback<Cart> callback3=new Callback<Cart>() {
                            @Override
                            public void onResponse(Call<Cart> call, Response<Cart> response) {
                                Call<CartItem> cartResponses=iProductApi.addToCart(response.body().getCartId(),cartItem);
                                Callback<CartItem> callback1=new Callback<CartItem>() {
                                    @Override
                                    public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                                        Toast.makeText(ProductActivity.this,"Added to cart",Toast.LENGTH_SHORT).show();
                                        addToCart.setVisibility(View.GONE);
                                        buyNow.setVisibility(View.INVISIBLE);
                                        addedCart.setVisibility(View.VISIBLE);
                                        addedCart.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent=new Intent(ProductActivity.this,CartActivity.class);
                                                startActivity(intent);
                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailure(Call<CartItem> call, Throwable t) {
                                        Toast.makeText(ProductActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                };
                                cartResponses.enqueue(callback1);
                            }

                            @Override
                            public void onFailure(Call<Cart> call, Throwable t) {
                                Toast.makeText(ProductActivity.this,"Cart Id:"+t.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        };
                        responses3.enqueue(callback3);
                    }
                });
            }

            @Override
            public void onFailure(Call<InventoryMerchantStore> call, Throwable t) {
                Toast.makeText(ProductActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        };
        responses.enqueue(callback);
    }
}