package com.kaushik.newsapplication;

import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.kaushik.newsapplication.adapters.DataAdapter;
import com.kaushik.newsapplication.entities.ApiNewsListResponse;
import com.kaushik.newsapplication.entities.News;
import com.kaushik.newsapplication.viewmodels.ListNewsViewModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends LifecycleActivity {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private final String TAG = MainActivity.class.getName();
    private ListNewsViewModel mViewModel;
    private RecyclerView mNewsRecyclerView;
    private ProgressDialog mDialog;
    private DataAdapter mAdapter;
    private Observer<ApiNewsListResponse> dataObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((NewsApplication) getApplication()).getAppComponent().inject(this);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ListNewsViewModel.class);

        mNewsRecyclerView = (RecyclerView) findViewById(R.id.newsRecyclerView);

        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle(getString(R.string.progress_title));
        mDialog.setMessage(getString(R.string.progress_body));
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);

        mNewsRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false)
        );
        mNewsRecyclerView.hasFixedSize();
        mNewsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                mNewsRecyclerView.getContext(), LinearLayoutManager.VERTICAL
        );
        mNewsRecyclerView.addItemDecoration(mDividerItemDecoration);
        mAdapter = new DataAdapter(getLayoutInflater(), this);
        mNewsRecyclerView.setAdapter(mAdapter);

        setProgress(true);
        //mViewModel.loadNewsList();

        dataObserver = new Observer<ApiNewsListResponse>() {
            @Override
            public void onChanged(@Nullable ApiNewsListResponse apiNewsListResponse) {
                if (apiNewsListResponse.getError() != null) {
                    handleError(apiNewsListResponse.getError());
                } else {
                    handleResponse(apiNewsListResponse.getDatas());
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.getApiResponse().observe(this, dataObserver);
    }

    private void handleResponse(List<News> newsList) {
        setProgress(false);
        if (newsList != null && newsList.size() > 0) {
            mAdapter.addNews(newsList);
        } else {
            mAdapter.clearNews();
            Toast.makeText(
                    this,
                    "No issues found.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void handleError(Throwable error) {
        setProgress(false);
        mAdapter.clearNews();
        Log.e(TAG, "error occured: " + error.toString());
        Toast.makeText(this, "Oops! Some error occured.", Toast.LENGTH_SHORT).show();
    }

    public void setProgress(boolean flag) {
        if (flag) {
            mDialog.show();
        } else {
            mDialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewModel.getApiResponse().removeObserver(dataObserver);
    }
}
