package com.example.currencyexchange.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is used to initialize the Retrofit library
 *dilshad
 */
public class RetrofitApiClientCall {
    private static final String BASE_URL = WebServiceCallUrlClass.WEB_SERVICE_URL;
    private static Retrofit retrofit = null;

    /**
     * This method is used to initialize the base url into the Retrofit library
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
