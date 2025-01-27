package com.example.myapplication.db;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides a Retrofit client instance.
 */
public class RetrofitClient {
    private static Retrofit retrofit = null;

    /**
     * Returns a Retrofit client instance for the given base URL.
     *
     * @param baseUrl the base URL
     * @return the Retrofit client instance
     */
    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}