package com.kaushik.newsapplication.di;

import com.kaushik.newsapplication.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Kaushik on 30-08-2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);
}
