package com.example.unisole.interfaces;

import retrofit2.Call;
import com.example.unisole.models.Search;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ISearchApi {

@GET("search/findByName/{name}")
    Call<List<Search>> getPosts(@Path("name") String searchQuery);
}
