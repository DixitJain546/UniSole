package com.example.unisole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.unisole.adapters.ProductAdapter;
import com.example.unisole.builder.RetrofitBuilder;
import com.example.unisole.interfaces.IProductApi;
import com.example.unisole.models.Category;
import com.example.unisole.models.Inventory;
import com.example.unisole.models.InventoryPK;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductListActivity extends AppCompatActivity implements ProductAdapter.ProductClick {
    ArrayList<Inventory> list=new ArrayList<>();
    Retrofit retrofit= RetrofitBuilder.getInstance();
    IProductApi iProductApi=retrofit.create(IProductApi.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        String categoryId=getIntent().getStringExtra("categoryId");
        showProductList(categoryId);
        findViewById(R.id.img_left_arrow_product_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProductListActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showProductList(String categoryId) {
        Call<List<Inventory>> responses=iProductApi.getProductByCategoryId(categoryId);
        Callback<List<Inventory>> callback=new Callback<List<Inventory>>() {
            @Override
            public void onResponse(Call<List<Inventory>> call, Response<List<Inventory>> response) {
                for(Inventory p:response.body()){
                    list.add(p);
                }
                RecyclerView recyclerView=findViewById(R.id.rv_product_list);
                ProductAdapter recyclerViewAdapter = new ProductAdapter(list, ProductListActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
                Log.d("Category Id : ",categoryId);

            }

            @Override
            public void onFailure(Call<List<Inventory>> call, Throwable t) {
                Toast.makeText(ProductListActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        };
        responses.enqueue(callback);

    }

    @Override
    public void onProductClick(String productId, int sizeId, int colorId, String merchantId) {
        Intent intent=new Intent(ProductListActivity.this,ProductActivity.class);
        intent.putExtra("productId", productId );
        intent.putExtra("sizeId", sizeId );
        intent.putExtra("colorId", colorId);
        intent.putExtra("merchantId", merchantId);
        startActivity(intent);
    }
}