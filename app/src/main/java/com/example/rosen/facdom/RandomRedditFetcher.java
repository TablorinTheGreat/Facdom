package com.example.rosen.facdom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class RandomRedditFetcher extends FetchFact {
    private String subreddit;
    private String title;

    public RandomRedditFetcher(String subreddit, String title) {
        this.subreddit = subreddit;
        this.title = title;
    }


    @Override
    protected String getUrl() {
        return "https://www.reddit.com/r/"+subreddit+"/top.json?sort=top&t=week&limit=100";
    }

    @Override
    String getTitle() {
        return title;
    }

    @Override
    public fact fetchRandomFact() {
        JSONObject response = makeRequest(getUrl());
        if(response == null) return null;
        try {
            JSONArray facts = response.getJSONObject("data").getJSONArray("children");
            JSONObject randomFact = facts.getJSONObject(new Random().nextInt(facts.length())).getJSONObject("data");
            String factText = randomFact.getString("title");
            String factSrc = randomFact.getString("url");
            return new fact(factText, factSrc);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
