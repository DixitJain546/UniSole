package com.example.unisole.interfaces;

import com.example.unisole.models.GoogleUser;
import com.example.unisole.models.LoginHistory;
import com.example.unisole.models.Token;
import com.example.unisole.models.User;
import com.example.unisole.models.UserData;
import com.example.unisole.models.UserLoginDetail;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ILoginApi {
    @POST("user/signup/{roleId}")
    Call<Token> signup(@Body User user, @Path("roleId") int role);
    @POST("user/signin/")
    Call<Token> signin(@Body UserLoginDetail userLogin);
    @POST("user/signin/google")
    Call<Token> loginWithGoogle(@Body GoogleUser googleUser);

    @GET("user/history/{email}")
    Call<ArrayList<LoginHistory>> loginHistory(@Path("email") String email);

    @PUT("user/update/")
    Call<User> updateProfile(@Body UserData userData);

    @GET("user/getprofile/{email}")
    Call<User> getProfie(@Path("email") String email);
}
