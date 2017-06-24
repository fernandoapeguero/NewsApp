package com.fernandoapeguero.newsapp;

/**
 * Created by admin on 6/23/17.
 */

public class NewsData  {
    private String mWebTitle;
    private String mWebSectionId;
    private String mPublicationDate;
    private String mWebUrl;


    public NewsData (String webTitle ,String webSectionId ,String publishedDate ,String webUrl){

        mWebTitle = webTitle;
        mPublicationDate = publishedDate;
        mWebSectionId = webSectionId ;
        mWebUrl = webUrl;


    }

    public String getmWebTitle(){
        return mWebTitle;
    }

    public String getmWebSectionId(){
        return mWebSectionId;

    }

    public String getmPublicationDate(){
        return mPublicationDate;
    }

    public String getmWebUrl(){
        return mWebUrl;
    }

}
