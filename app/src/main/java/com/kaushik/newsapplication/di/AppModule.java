package com.kaushik.newsapplication.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.kaushik.newsapplication.api.NewsApiService;
import com.kaushik.newsapplication.repositories.INewsRepository;
import com.kaushik.newsapplication.repositories.NewsRepository;
import com.kaushik.newsapplication.viewmodels.ListNewsViewModel;
import com.kaushik.newsapplication.viewmodels.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kaushik on 30-08-2017.
 */

@Module
public class AppModule {

    public static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/";

    @Provides
    @Singleton
    NewsApiService provideNewsApiService() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(NewsApiService.class);
    }

    @Provides
    @Singleton
    INewsRepository provideIssueRepository(NewsRepository repository) {
        return repository;
    }

    @Provides
    ViewModel provideListIssuesViewModel(ListNewsViewModel viewModel) {
        return viewModel;
    }

    @Provides
    ViewModelProvider.Factory provideListIssuesViewModelFactory(ViewModelFactory factory) {
        return factory;
    }

}
