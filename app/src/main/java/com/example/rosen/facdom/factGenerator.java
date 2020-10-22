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

    private static class FactFetcherTask extends AsyncTask<OnFactRecived, Void, fact> {
        OnFactRecived onFactRecived;
        FetchFact factFetcher;

        public void setFactFetcher(FetchFact factFetcher) {
            this.factFetcher = factFetcher;
        }

        @Override
        protected fact doInBackground(OnFactRecived... onFactReciveds) {
            onFactRecived = onFactReciveds[0];
            if (factFetcher == null) return null;
            return factFetcher.fetchRandomFact();
        }

        @Override
        protected void onPostExecute(fact fact) {
            if (fact != null)
                this.onFactRecived.factRecieved(fact, factFetcher.getTitle());
        }
    }

    public static void getRandomFact(OnFactRecived fc) {
        FactFetcherTask task = new FactFetcherTask();
        task.setFactFetcher(RandomFetcherGenerator.getRandomFetcher());
        task.execute(fc);
    }

}
