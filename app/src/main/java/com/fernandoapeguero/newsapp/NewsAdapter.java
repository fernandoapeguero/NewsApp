package com.fernandoapeguero.newsapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 6/23/17.
 */

public class NewsAdapter extends ArrayAdapter<NewsData> {

    private static final String SEPARATOR = "T";
    private static final String TIME_SEPARATOR = "Z";

    public NewsAdapter(Activity context, ArrayList<NewsData> news) {
        super(context , 0 , news);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView != null){
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        }
        NewsData currentNews = getItem(position);

        if (currentNews != null) {
            holder.webTitle.setText(currentNews.getmWebTitle());
        }

        if (currentNews != null) {
            holder.sectionId.setText(currentNews.getmWebSectionId());

        }

        String originalDateAndTime = currentNews.getmPublicationDate();

        String date;
        String time = "";

        if (originalDateAndTime.contains(SEPARATOR)){

            String[] parts = originalDateAndTime.split(SEPARATOR);
            date = parts[0];

            if (parts[1].contains("Z")){
              String currentTime = parts[1].toString();
                String[] theTime = currentTime.split(TIME_SEPARATOR);
               time = theTime[0];
            }

        } else {
            date = "not Available";
            time = "not available";
        }

        holder.published.setText(date);
        holder.time.setText(time);
        return convertView;
    }

    /**
     * setting up @Butterknife view holder
     */
     static class ViewHolder {

        @BindView(R.id.web_title) TextView webTitle;
        @BindView(R.id.section_id) TextView sectionId;
        @BindView(R.id.date) TextView published;
        @BindView(R.id.time) TextView time;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
