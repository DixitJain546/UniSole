package com.example.unisole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import retrofit2.Call;

import com.example.unisole.builder.RetrofitBuilder;
import com.example.unisole.interfaces.ISearchApi;
import com.example.unisole.models.Search;
import com.example.unisole.adapters.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.SearchClick {

    private SearchView etSearchQuery;
    private Button btnSearch;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        etSearchQuery=findViewById(R.id.sv_searchbar);
        btnSearch=findViewById(R.id.btn_search);
        recyclerView=findViewById(R.id.rv_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInit();
            }
        });
    }
    private void searchInit() {
        String searchQuery= etSearchQuery.getQuery().toString();
        Retrofit retrofit= RetrofitBuilder.getInstance();
        ISearchApi iSearchApi =retrofit.create(ISearchApi.class);
        Call<List<Search>> responses= iSearchApi.getPosts(searchQuery);
        Callback<List<Search>> callback=new Callback<List<Search>>() {
            @Override
            public void onResponse(Call<List<Search>> call, Response<List<Search>> response) {

                if(null!=response.body()) {
                    ArrayList<Search> searches = new ArrayList<>();
                    for (Search arr : response.body()) {
                        searches.add(arr);
                    }
                    SearchAdapter searchRecyclerAdapter=new SearchAdapter(searches,SearchActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    recyclerView.setAdapter(searchRecyclerAdapter);

                }
            }
            @Override
            public void onFailure(Call<List<Search>> call, Throwable t) {
                Log.d("RecyclerView","Cannot hit api"+t.getMessage());
            }
        };
        responses.enqueue(callback);
    }

    @Override
    public void onSearchClick(String productId, int sizeId, int colorId, String merchantId) {
        Intent intent=new Intent(SearchActivity.this, ProductActivity.class);
        intent.putExtra("productId", productId );
        intent.putExtra("sizeId", sizeId );
        intent.putExtra("colorId", colorId);
        intent.putExtra("merchantId", merchantId);
        startActivity(intent);
    }
}