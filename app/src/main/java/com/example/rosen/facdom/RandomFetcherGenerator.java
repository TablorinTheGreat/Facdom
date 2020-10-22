package com.example.rosen.facdom;

import java.util.Random;

public abstract class RandomFetcherGenerator {
    private static final FetchFact[] fetchers = new FetchFact[]{ShowerThoughtsFetcherGenerator.getShowerThoughtsFetcher(),new RandomFactApiFetcher()};
    public static FetchFact getRandomFetcher(){
        return fetchers[new Random().nextInt(fetchers.length)];
    }
}
