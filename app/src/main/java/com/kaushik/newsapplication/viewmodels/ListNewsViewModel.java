package com.kaushik.newsapplication.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kaushik.newsapplication.entities.ApiNewsListResponse;
import com.kaushik.newsapplication.entities.News;
import com.kaushik.newsapplication.repositories.NewsRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Kaushik on 29-08-2017.
 */

public class ListNewsViewModel extends ViewModel {

    private MediatorLiveData<ApiNewsListResponse> mApiResponse;
    private NewsRepository mNewsRepository;
    private boolean flag=true;

    @Inject
    public ListNewsViewModel(NewsRepository newsRepository) {
        mApiResponse = new MediatorLiveData<>();
        mNewsRepository = newsRepository;
        loadNewsList();
    }

    @NonNull
    public LiveData<ApiNewsListResponse> getApiResponse() {

        return mApiResponse;
    }

    public LiveData<ApiNewsListResponse> loadNewsList() {
        mApiResponse.addSource(
                mNewsRepository.getNewsList(),
                new Observer<ApiNewsListResponse>() {
                    @Override
                    public void onChanged(@Nullable ApiNewsListResponse apiNewsListResponse) {
                        mApiResponse.setValue(apiNewsListResponse);
                    }
                }
        );
        return mApiResponse;
    }
}
