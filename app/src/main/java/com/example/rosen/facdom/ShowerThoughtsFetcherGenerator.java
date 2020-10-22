package com.example.rosen.facdom;
// this is class basiclly exists to keep data because i was too lazy to add a db to the project ;)
// in case of adding db to project remove this class
public abstract class ShowerThoughtsFetcherGenerator {
    private static final String subreddit = "showerthoughts";
    private static final String title = "reddit/ShowerThoughts";
    public static RandomRedditFetcher getShowerThoughtsFetcher(){
        return new RandomRedditFetcher(subreddit, title);
    }
}
