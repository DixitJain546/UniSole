package com.example.unisole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.unisole.adapters.OrderAdapter;
import com.example.unisole.builder.RetrofitBuilder;
import com.example.unisole.interfaces.IProductApi;
import com.example.unisole.models.OrderItem;
import com.example.unisole.models.OrderItemData;
import com.example.unisole.models.Token;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderActivity extends AppCompatActivity implements OrderAdapter.OrderClick {
    Retrofit retrofit= RetrofitBuilder.getInstance();
    IProductApi iProductApi=retrofit.create(IProductApi.class);
    ArrayList<OrderItemData> list=new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        findViewById(R.id.img_left_arrow_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("com.example.unisole",MODE_PRIVATE);
        String token=sharedPreferences.getString("TOKEN","");
        JWT jwt=new JWT(token);
        Claim email=jwt.getClaim("email");
        getOrderList(email.asString());
    }
    public void  getOrderList(String email){
        Call<List<OrderItemData>> responses=iProductApi.getOrderList(email);
        Callback<List<OrderItemData>> callback=new Callback<List<OrderItemData>>() {
            @Override
            public void onResponse(Call<List<OrderItemData>> call, Response<List<OrderItemData>> response) {
                for(OrderItemData orderItem:response.body()){
                    list.add(orderItem);
                }
                Toast.makeText(OrderActivity.this,"Reached",Toast.LENGTH_LONG).show();;
                recyclerView=findViewById(R.id.rv_order_list);
                OrderAdapter orderAdapter=new OrderAdapter(list,OrderActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                recyclerView.setAdapter(orderAdapter);

            }

            @Override
            public void onFailure(Call<List<OrderItemData>> call, Throwable t) {
                Toast.makeText(OrderActivity.this,t.getMessage(),Toast.LENGTH_SHORT);
            }
        };
        responses.enqueue(callback);
    }

    @Override
    public void onOrderClick(String orderItemId, double rating) {
        Call<Token> responses=iProductApi.addRating(orderItemId,rating);
        Callback<Token> callback=new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        };
        responses.enqueue(callback);
    }
}