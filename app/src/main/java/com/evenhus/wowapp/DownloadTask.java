package com.evenhus.wowapp;

import android.os.AsyncTask;
import android.os.Bundle;
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

public class DownloadTask extends AsyncTask<String, Void, String> {

    TextView name;
    TextView itemReward;
    TextView icon;
    TextView points;

    ListView rewards;
    ArrayList arrayList;
    ArrayAdapter adapter;

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection httpURLConnection = null;

        try {
            url = new URL(urls[0]);

            httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream in = httpURLConnection.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data != -1) {
                char current = (char) data;

                result += current;

                data = reader.read();
            }

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject jsonObject = new JSONObject(result);

            name.setText("Name:"+ jsonObject.getString("title"));
            points.setText("Points: " + jsonObject.getString("points"));
            icon.setText("Icon: " + jsonObject.getString("icon"));
            //itemReward.setText(jsonObject.getString("reward"));
/*
            String rewards = jsonObject.getString("rewardItems");
            JSONArray arr = new JSONArray(rewards);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject jsonPart = arr.getJSONObject(i);
                arrayList.add(jsonPart);
            }*/

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
