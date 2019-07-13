package com.example.currencyexchange.rest;

import com.example.currencyexchange.model.Currency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author dilshad
 * This interface is used to provide the call back method to Retrofit to get the requested url
 */
public interface ApiInterface {

    /**
     * this call back method will initialise the URL and return the
     * LinkInBioPosts to the initialized it
     */
    @GET(WebServiceCallUrlClass.WEB_SERVICE_URL_FOR_POST)
    Call<Currency> getLinkInBioPostLists();



    @GET("2019-07-12?")
    Call<Currency> getLatestRate(@Query("base") String base, @Query("symbols") String currency1, @Query("symbols") String currency2);

}

