package com.evenhus.wowapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String apiKey = "p28jsb432q4vdd3zb9xtcdss6bpgatt3";

    EditText editText;

    DownloadTask downloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.searchId);
        downloadTask = new DownloadTask();

        downloadTask.name = (TextView)findViewById(R.id.name);
        downloadTask.icon = (TextView)findViewById(R.id.iconView);
        downloadTask.itemReward = (TextView)findViewById(R.id.reward);
        downloadTask.points = (TextView)findViewById(R.id.points);
        downloadTask.rewards = (ListView)findViewById(R.id.rewards);

        downloadTask.arrayList = new ArrayList<String>();
        downloadTask.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, downloadTask.arrayList);
        downloadTask.rewards.setAdapter(downloadTask.adapter);
    }



    public void search(View view){

        String idValue = editText.getText().toString();

        int id = Integer.parseInt(idValue);

        System.out.println("test");


        downloadTask.execute("https://eu.api.battle.net/wow/achievement/"+ id +"?locale=en_GB&apikey="+apiKey);
    }
}
