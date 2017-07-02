package com.fernandoapeguero.newsapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsData>> {

    private static final String HTTP_REQUEST_URL = "http://content.guardianapis.com/search?from-date=2001-01-01&to-date=2017-12-01&page-size=10&q=renewable%20energy&api-key=fba7be92-00a1-4580-b783-918c17fb0bd8";
    private NewsAdapter mAdapter;

    private static final int NEWS_MANAGER_ID = 1;

    @BindView(R.id.progbress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ListView listView = (ListView) findViewById(R.id.list);

        mAdapter = new NewsAdapter(this, new ArrayList<NewsData>());

        listView.setAdapter(mAdapter);

        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(NEWS_MANAGER_ID, null, this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NewsData currentNews = mAdapter.getItem(position);


                Uri newsUri = Uri.parse(currentNews.getmWebUrl());


                Intent webNewsIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                startActivity(webNewsIntent);
            }
        });

    }

    @Override
    public Loader<List<NewsData>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, HTTP_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsData>> loader, List<NewsData> data) {
        progressBar.setVisibility(View.GONE);

        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<NewsData>> loader) {
        mAdapter.clear();

    }
}
