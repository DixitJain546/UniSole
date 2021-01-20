package com.example.unisole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.unisole.builder.RetrofitBuilder;
import com.example.unisole.interfaces.ILoginApi;
import com.example.unisole.models.LoginHistory;
import com.example.unisole.models.User;
import com.example.unisole.models.UserData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserProfileActivity extends AppCompatActivity {
    Retrofit retrofit= RetrofitBuilder.getInstance();
    ILoginApi iLoginApi=retrofit.create(ILoginApi.class);
    ImageView editProfile;
    ImageView saveProfile;
    TextView userName;
    TextView emailAddress;
    EditText etUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ImageView loginHistory=findViewById(R.id.iv_login_history);
        etUserName=findViewById(R.id.et_user_name_profile);
        editProfile=findViewById(R.id.btn_edit_profile);
        saveProfile=findViewById(R.id.iv_save_profile);
        userName=findViewById(R.id.tv_user_name_profile);
        emailAddress=findViewById(R.id.tv_email_profile);

        SharedPreferences sharedPreferences=getSharedPreferences("com.example.unisole",MODE_PRIVATE);
        String token=sharedPreferences.getString("TOKEN","");
        JWT jwt=new JWT(token);
        Claim emailClaim=jwt.getClaim("email");
        String email=emailClaim.asString();
        findViewById(R.id.img_left_arrow_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfileActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        loginHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfileActivity.this,LoginHistoryActivity.class);
                startActivity(intent);
            }
        });
        getUserData(email);
    }
    public void getUserData(String email){
        emailAddress.setText(email);
        Call<User> responses=iLoginApi.getProfie(email);
        Callback<User> callback=new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userName.setText(response.body().getUserName());
                editProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userName.setVisibility(View.INVISIBLE);
                        etUserName.setVisibility(View.VISIBLE);
                        editProfile.setVisibility(View.INVISIBLE);
                        saveProfile.setVisibility(View.VISIBLE);
                        saveProfile.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String user=etUserName.getText().toString();
                                UserData userData=new UserData();
                                userData.setUserName(user);
                                userData.setEmail(email);
                                Call<User> responses1=iLoginApi.updateProfile(userData);
                                Callback<User> callback1=new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        etUserName.setVisibility(View.INVISIBLE);
                                        saveProfile.setVisibility(View.INVISIBLE);
                                        userName.setVisibility(View.VISIBLE);
                                        editProfile.setVisibility(View.VISIBLE);
                                        userName.setText(response.body().getUserName());
                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                    Toast.makeText(UserProfileActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                };
                                responses1.enqueue(callback1);
                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UserProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        responses.enqueue(callback);
    }
}