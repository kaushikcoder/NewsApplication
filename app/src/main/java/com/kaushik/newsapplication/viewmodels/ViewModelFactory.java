package com.kaushik.newsapplication.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;

/**
 * Created by Kaushik on 30-08-2017.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private ListNewsViewModel mViewModel;

    @Inject
    public ViewModelFactory(ListNewsViewModel viewModel) {
        this.mViewModel = viewModel;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListNewsViewModel.class)) {
            return (T) mViewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
