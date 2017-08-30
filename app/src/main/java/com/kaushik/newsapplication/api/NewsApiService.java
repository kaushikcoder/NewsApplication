package com.kaushik.newsapplication.api;

import com.kaushik.newsapplication.entities.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Kaushik on 29-08-2017.
 */

public interface NewsApiService {

    @GET("topstories.json")
    Call<List<Integer>> getNewsIds();

    @GET("item/{id}.json")
    Call<News> getNews(@Path("id") int id);
}
