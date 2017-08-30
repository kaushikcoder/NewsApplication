package com.kaushik.newsapplication.entities;

import java.util.List;

/**
 * Created by Kaushik on 29-08-2017.
 */

public class ApiNewsListResponse {

    private List<News> datas;
    private Throwable error;

    public ApiNewsListResponse(List<News> datas) {
        this.datas = datas;
        this.error = null;
    }

    public ApiNewsListResponse(Throwable error) {
        this.error = error;
        this.datas = null;
    }

    public List<News> getDatas() {
        return datas;
    }

    public Throwable getError() {
        return error;
    }
}
