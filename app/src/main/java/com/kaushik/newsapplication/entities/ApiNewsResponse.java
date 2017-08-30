package com.kaushik.newsapplication.entities;


/**
 * Created by Kaushik on 29-08-2017.
 */

public class ApiNewsResponse {

    private News data;
    private Throwable error;

    public void setData(News datas) {
        this.data = data;
        this.error = null;
    }

    public void setError(Throwable error) {
        this.error = error;
        this.data = null;
    }

    public News getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }
}
