package com.example.rosen.facdom;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class factGenerator {
    private final static String urlString = "https://uselessfacts.jsph.pl/random.json?language=en";

    private static class FactFetcher extends AsyncTask<OnFactRecived, Void, fact> {
        OnFactRecived onFactRecived;

        @Override
        protected fact doInBackground(OnFactRecived... onFactReciveds) {
            try {
                this.onFactRecived = onFactReciveds[0];
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    JSONObject factJson = new JSONObject(StreamReader.readInput(in));
                    return new fact(factJson.getString("text"), "https://www.google.com/search?q=" + factJson.getString("text").replace(' ', '+'));
                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(fact fact) {
            if (fact != null)
                this.onFactRecived.factRecieved(fact);
        }
    }

    public static void getRandomFact(OnFactRecived fc) {
        new FactFetcher().execute(fc);
    }

}
