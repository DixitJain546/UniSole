package com.example.unisole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.unisole.adapters.CartAdapter;
import com.example.unisole.builder.RetrofitBuilder;
import com.example.unisole.interfaces.IProductApi;
import com.example.unisole.models.Cart;
import com.example.unisole.models.CartItem;
import com.example.unisole.models.CartItemData;
import com.example.unisole.models.CartItemListItem;
import com.example.unisole.models.Order;
import com.example.unisole.models.OrderData;
import com.example.unisole.models.Token;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartClick {
    ArrayList<CartItemData> list=new ArrayList<>();
    List<CartItem> cartList=new ArrayList<>();
    Retrofit retrofit= RetrofitBuilder.getInstance();
    IProductApi iProductApi=retrofit.create(IProductApi.class);
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    TextView grandTotal;
    Button placeOrderbtn;
    double sum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView=findViewById(R.id.rv_cart);
        placeOrderbtn=findViewById(R.id.btn_place_order);
        grandTotal=findViewById(R.id.tv_total_price_cart);
        findViewById(R.id.img_left_arrow_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("com.example.unisole",MODE_PRIVATE);
        String token=sharedPreferences.getString("TOKEN","");
        JWT jwt=new JWT(token);
        Claim email=jwt.getClaim("email");

        //int no=jwt.describeContents();
        //Toast.makeText(CartActivity.this,email.asString(),Toast.LENGTH_LONG).show();
//        Log.d("email",email);
       getCartItems(email.asString());


    }
    public void getCartItems(String email)
    {
        Call<Cart> responses=iProductApi.getCart(email);
        Callback<Cart> callback=new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Call<List<CartItemData>> responses1=iProductApi.getCartItems(response.body().getCartId());
                Callback<List<CartItemData>> callback1=new Callback<List<CartItemData>>() {
                    @Override
                    public void onResponse(Call<List<CartItemData>> call, Response<List<CartItemData>> response) {
                        for(CartItemData cartItemData:response.body()) {
                            list.add(cartItemData);
                            cartList.add(cartItemData.getCartItem());
                            sum=sum+cartItemData.getCartItem().getPrice();
                        }
                            if(list.size()==0)
                                placeOrderbtn.setVisibility(View.INVISIBLE);
                        grandTotal.setText(String.valueOf(sum));
                        cartAdapter=new CartAdapter(list,CartActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
                        recyclerView.setAdapter(cartAdapter);
                        OrderData orderData=new OrderData();
                        orderData.setCartItemList(cartList);
                        orderData.setUserId(email);

                        placeOrderbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Call<Order> orderResponses=iProductApi.placeOrder(orderData);
                                Callback<Order> callback2=new Callback<Order>() {
                                    @Override
                                    public void onResponse(Call<Order> call, Response<Order> response) {
                                        Toast.makeText(CartActivity.this,"Order Placed Successfull",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(CartActivity.this,HomeActivity.class);
                                        startActivity(intent);
                                        placeOrderbtn.setVisibility(View.INVISIBLE);
                                    }

                                    @Override
                                    public void onFailure(Call<Order> call, Throwable t) {

                                    }
                                };
                                orderResponses.enqueue(callback2);
                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<List<CartItemData>> call, Throwable t) {
                        Log.d("CartActivity : ",t.getMessage());
                    }
                };
                responses1.enqueue(callback1);
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        };
        responses.enqueue(callback);
    }

    @Override
    public void onCartClick(String cartItemId,CartItemData cartItemData) {
        Call<Token> responses=iProductApi.removeFromCart(cartItemId);
        Callback<Token> callback=new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if ("Item Deleted".equals(response.body().getToken())) {
                    list.remove(cartItemData);
                    cartAdapter.notifyDataSetChanged();
                    sum=0;
                    for(CartItemData cartItemData1:list)
                        sum=sum+cartItemData1.getCartItem().getPrice();
                    grandTotal.setText(String.valueOf(sum));
                    if(list.size()==0)
                        placeOrderbtn.setVisibility(View.INVISIBLE);

                    Toast.makeText(CartActivity.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(CartActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        };
        responses.enqueue(callback);
    }
}