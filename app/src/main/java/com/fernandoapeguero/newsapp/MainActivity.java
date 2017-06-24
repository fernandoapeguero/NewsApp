package com.fernandoapeguero.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<NewsData> storyOfTheDay = new ArrayList<>();
        storyOfTheDay.add(new NewsData("Trump is out of italy treaty ","Enviroment", "2017-05-20","http://google.com" ));
        storyOfTheDay.add(new NewsData("odebretch corruction of the world  ","politics ", "2016-02-20","http://google.com" ));
        storyOfTheDay.add(new NewsData("spain having fun in the summer ","Vacations ", "2017-06-20","http://google.com" ));
        storyOfTheDay.add(new NewsData("las amas de casa estan artas de como estan siendo abudas por los empleadores mas en nuestor noticiero de las 11:00pm ","Enviroment", "2017-05-20","http://google.com" ));
        storyOfTheDay.add(new NewsData("Trump is out of italy treaty ","Enviroment", "2017-05-20","http://google.com" ));
        storyOfTheDay.add(new NewsData("Trump is out of italy treaty ","Enviroment", "2017-05-20","http://google.com" ));
        storyOfTheDay.add(new NewsData("Trump is out of italy treaty ","Enviroment", "2017-05-20","http://google.com" ));
        storyOfTheDay.add(new NewsData("Trump is out of italy treaty ","Enviroment", "2017-05-20","http://google.com" ));


        ListView listView = (ListView) findViewById(R.id.list);

        NewsAdapter adapter = new NewsAdapter(this,storyOfTheDay);

        listView.setAdapter(adapter);


    }
}
