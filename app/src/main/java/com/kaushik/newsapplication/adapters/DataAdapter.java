package com.kaushik.newsapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaushik.newsapplication.NewsActivity;
import com.kaushik.newsapplication.R;
import com.kaushik.newsapplication.entities.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kaushik on 30-08-2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.Holder> {

    private final LayoutInflater mInflator;
    private final Context mContext;
    private List<News> mNewsList;

    public DataAdapter(LayoutInflater inflator, Context context) {
        mInflator = inflator;
        mNewsList = new ArrayList<>();
        mContext = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflator.inflate(R.layout.news_row, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if(mNewsList.get(position).getTitle()!=null) {
            holder.mTextViewTitle.setText(mNewsList.get(position).getTitle());
            holder.mTextViewId.setText(mNewsList.get(position).getId()+"");
        }
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void addNews(List<News> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    public void clearNews() {
        mNewsList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView mTextViewTitle;
        TextView mTextViewId;

        public Holder(View v) {
            super(v);
            mTextViewTitle = (TextView) v.findViewById(R.id.newsTitle);
            mTextViewId = (TextView) v.findViewById(R.id.newsId);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = getLayoutPosition();
                    Log.d("onclick", itemPosition + "");
                    if(mNewsList.get(itemPosition).getUrl()!=null){
                        Intent nextIntent = new Intent(mContext, NewsActivity.class);
                        nextIntent.putExtra("url", mNewsList.get(itemPosition).getUrl());
                        mContext.startActivity(nextIntent);
                    }
                }
            });
        }
    }
}
