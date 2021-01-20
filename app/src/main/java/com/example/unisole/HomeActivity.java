package com.example.unisole;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.unisole.adapters.HomeAdapter;
import com.example.unisole.adapters.RecommendationAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements RecommendationAdapter.RecommendationClick {

    private DrawerLayout d1;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    ArrayList<String> list=new ArrayList<>();
    RecyclerView rvCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rvCart=findViewById(R.id.recyclerView);
        list.add("Boots");
        list.add("Heels");
        list.add("Shoes");
        list.add("Sneakers");
        list.add("Sandals");
        HomeAdapter homeAdapter=new HomeAdapter(list,HomeActivity.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(HomeActivity.this);

        rvCart.setLayoutManager(new GridLayoutManager(this, 2));
        rvCart.setAdapter(homeAdapter);
        d1=(DrawerLayout)findViewById(R.id.d1);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,d1,R.string.Open,R.string.Close);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        d1.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


       final NavigationView navigationView = (NavigationView)findViewById(R.id.nv);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                 if (id == R.id.search) {
                    Intent intent=new Intent(HomeActivity.this, SearchActivity.class);
                    startActivity(intent);


                } else if (id == R.id.user_profile) {
                    Intent intent=new Intent(HomeActivity.this,UserProfileActivity.class);
                    startActivity(intent);


                } else if (id == R.id.Your_order) {

                    Intent intent=new Intent(HomeActivity.this,OrderActivity.class);
                    startActivity(intent);

                } else if (id == R.id.Shopping_cart) {

                    Intent intent=new Intent(HomeActivity.this,CartActivity.class);
                    startActivity(intent);

                } else if(id == R.id.log_out){
                    SharedPreferences sharedPreferences=getSharedPreferences("com.example.unisole",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.remove("TOKEN");
                    editor.commit();
                    Intent intent=new Intent(HomeActivity.this,WelcomeActivity.class);
                    startActivity(intent);
                }
                return true;


            }


    });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


    @Override
    public void onProductClick(String productId, int sizeId, int colorId, String merchantId) {
        Intent intent=new Intent(HomeActivity.this,ProductActivity.class);
        intent.putExtra("productId", productId );
        intent.putExtra("sizeId", sizeId );
        intent.putExtra("colorId", colorId);
        intent.putExtra("merchantId", merchantId);
        startActivity(intent);

    }
}
