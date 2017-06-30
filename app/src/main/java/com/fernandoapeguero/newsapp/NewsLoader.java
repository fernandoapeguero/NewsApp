package com.fernandoapeguero.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by nico on 6/30/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsData>> {

   private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsData> loadInBackground() {

        if (mUrl == null){
            return null;
        }

        return NewsUtils.fetchNewsData(mUrl);
    }
}
