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
import com.example.unisole.adapters.LoginAdapter;
import com.example.unisole.builder.RetrofitBuilder;
import com.example.unisole.interfaces.ILoginApi;
import com.example.unisole.models.LoginHistory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginHistoryActivity extends AppCompatActivity {
    Retrofit retrofit=RetrofitBuilder.getInstance();
    ILoginApi iLoginApi=retrofit.create(ILoginApi.class);
    ArrayList<LoginHistory> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history);
        getLoginHistory();
        findViewById(R.id.img_left_arrow_login_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginHistoryActivity.this,UserProfileActivity
                        .class);
                startActivity(intent);
            }
        });
    }
    public void getLoginHistory(){
        SharedPreferences sharedPreferences=getSharedPreferences("com.example.unisole",MODE_PRIVATE);
        String token=sharedPreferences.getString("TOKEN","");
        JWT jwt=new JWT(token);
        Claim emailClaim=jwt.getClaim("email");
        String email=emailClaim.asString();
        Call<ArrayList<LoginHistory>> responses=iLoginApi.loginHistory(email);
        Callback<ArrayList<LoginHistory>> callback=new Callback<ArrayList<LoginHistory>>() {
            @Override
            public void onResponse(Call<ArrayList<LoginHistory>> call, Response<ArrayList<LoginHistory>> response) {
                for(LoginHistory loginHistory:response.body()){
                    list.add(loginHistory);
                }
                RecyclerView recyclerView=findViewById(R.id.rv_login_history);
                LoginAdapter loginAdapter=new LoginAdapter(list,LoginHistoryActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(LoginHistoryActivity.this));
                recyclerView.setAdapter(loginAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<LoginHistory>> call, Throwable t) {
                Toast.makeText(LoginHistoryActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        };
        responses.enqueue(callback);
    }
}