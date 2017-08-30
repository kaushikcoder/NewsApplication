package com.kaushik.newsapplication.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.kaushik.newsapplication.api.NewsApiService;
import com.kaushik.newsapplication.entities.ApiNewsListResponse;
import com.kaushik.newsapplication.entities.ApiNewsResponse;
import com.kaushik.newsapplication.entities.News;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kaushik on 29-08-2017.
 */

public class NewsRepository implements INewsRepository {

    @Inject
    NewsApiService mApiService;
    List<News> newsList = new ArrayList<News>();

    @Inject
    public NewsRepository() {
    }

    MutableLiveData<ApiNewsListResponse> liveData = new MutableLiveData<>();

    @Override
    public LiveData<ApiNewsListResponse> getNewsList() {

        Call<List<Integer>> call = mApiService.getNewsIds();
        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                Log.d("news id", "received : " + response.body().size());
                List<Integer> newsIds = response.body();

                for(Integer id : newsIds) {
                    Log.d("news id", ""+id);
                    getNewsDetails(id);
                }
            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                liveData.setValue(new ApiNewsListResponse(t));
            }
        });
        return liveData;
    }

    private void getNewsDetails(Integer id) {
        Call<News> call = mApiService.getNews(id);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                //Log.d("news details", response.body().getUrl());
                newsList.add(response.body());
                liveData.setValue(new ApiNewsListResponse(newsList));
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d("news details", "error " + t.getMessage());
            }
        });
    }
}
