package com.kaushik.newsapplication.repositories;

import android.arch.lifecycle.LiveData;

import com.kaushik.newsapplication.entities.ApiNewsListResponse;
import com.kaushik.newsapplication.entities.News;

import java.util.List;

/**
 * Created by Kaushik on 29-08-2017.
 */

public interface INewsRepository {
    LiveData<ApiNewsListResponse> getNewsList();
}
