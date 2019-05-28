package com.example.nasaappjava;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.BitSet;

public class GetPlanetsAsyncTask extends AsyncTask<String,String,String> {
    private String url;

    public GetPlanetsAsyncTask(String url) {
        this.url = url;
    }

    @Override
    protected String doInBackground(String... params) {

        JSONObject jsonObject = null;
        try {

            //GET OUR JSON
            URL url = new URL(this.url);
            HttpURLConnection urlConnection =  (HttpURLConnection) url.openConnection();

            InputStream inputStream  = new BufferedInputStream(urlConnection.getInputStream());


            //READ IT
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            for (String line; (line = r.readLine()) != null; ) {
                total.append(line).append('\n');
            }


             jsonObject = new JSONObject(total.toString());
            //System.out.println("json " + jsonObject.toString());
            //imageUrl = jsonObject.has("hdurl") ?  jsonObject.getString("hdurl") : "no hdurl";
            //planetJson = jsonObject.toString();




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();

    }




    }
