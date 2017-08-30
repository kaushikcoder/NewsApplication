package com.kaushik.newsapplication;

import android.app.Application;
import com.kaushik.newsapplication.di.AppComponent;
import com.kaushik.newsapplication.di.DaggerAppComponent;

/**
 * Created by Kaushik on 29-08-2017.
 */

public class NewsApplication extends Application {

    AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
