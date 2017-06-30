package com.fernandoapeguero.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 6/28/17.
 */

public class NewsUtils {

    private static final String LOG_TAG = NewsUtils.class.getName();

    private static URL createUrl (String stringUrl){
        URL url = null;

        try {
            url =  new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG," malformed error " + e);
        }

        return url;
    }

    private static String makeHttpRequest (URL url) throws IOException {

        String jsonResponse = "";

        if (url == null){
            return jsonResponse ;
        }

        HttpURLConnection  urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(1000 /* miliseconds */);
            urlConnection.setConnectTimeout(15000 /* miliseconds */);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200){
                Log.e(LOG_TAG,"response code " + urlConnection.getResponseCode());
               inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG,"error code " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(LOG_TAG,"error retrieving the news from json result , e");
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }

     return jsonResponse ;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null){
            InputStreamReader inputReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    public static List<NewsData> extractFeatureFromData(String jsonNews){

        if (TextUtils.isEmpty(jsonNews)){
            return null;
        }

        List<NewsData> news = new ArrayList<>();

        try {
            JSONObject baseResponse = new JSONObject(jsonNews);
            JSONObject response = baseResponse.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length() ; i++){
                JSONObject currentResult = results.getJSONObject(i);
                String title = currentResult.getString("webTitle");
                String sectionId = currentResult.getString("sectionId");
                String published;
                if (currentResult.has("webPublicationDate")) {
                     published = currentResult.getString("webPublicationDate");
                } else {
                      published = "Not Available";
                }
                String webUrl = currentResult.getString("webUrl");

                NewsData theNews = new NewsData(title,sectionId,published,webUrl);

                news.add(theNews);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return news;

    }

    public static List<NewsData> fetchNewsData(String requestUrl){

        URL url = createUrl(requestUrl);

        String jsonReponse = null;

        try {
            jsonReponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG,"problem making the http request" + e);
        }
         // change when finish extratdata method
        return extractFeatureFromData(jsonReponse);
    }
}
