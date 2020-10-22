package com.example.rosen.facdom;

import org.json.JSONException;
import org.json.JSONObject;

public class RandomFactApiFetcher extends FetchFact {
    private final static String urlString = "https://uselessfacts.jsph.pl/random.json?language=en";
    private final static String Title = "Random Fact";

    @Override
    protected String getUrl() {
        return urlString;
    }

    @Override
    String getTitle() {
        return Title;
    }

    @Override
    public fact fetchRandomFact() {
        JSONObject response = makeRequest(getUrl());
        if(response == null) return null;
        try {
            String factText = response.getString("text");
            return new fact(factText, "https://www.google.com/search?q=" + factText.replace(' ', '+'));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
