package com.example.currencyexchange.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.currencyexchange.model.Currency;
import com.example.currencyexchange.rest.ApiInterface;
import com.example.currencyexchange.rest.RetrofitApiClientCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * this is the repository class which will get the data from the web and pass it to the model view into
 * the LiveData format
 */
public class CurrencyDetailListRepositories {

    private static CurrencyDetailListRepositories instance;
    private static ApiInterface apiService;
    private String TAG = CurrencyDetailListRepositories.class.getName();
    private MutableLiveData<Currency> currencyMutableLiveData = new MutableLiveData<>();


    /**
     * this is singleton approach to initialize the repository class
     *
     * @return
     */
    public static CurrencyDetailListRepositories getInstance() {
        if (instance == null) {
            instance = new CurrencyDetailListRepositories();
        }
        apiService = RetrofitApiClientCall.getClient().create(ApiInterface.class);
        return instance;
    }

    /**
     * this method will return the mutableLive data to the ViewModel class
     *
     * @return
     */
    public MutableLiveData<Currency> getLinkInBioPosts(String base, String currency1, String currency2) throws Exception {
        getLinkInBioPostFromTheServer(base, currency1, currency2);
        return currencyMutableLiveData;
    }

    private void getLinkInBioPostFromTheServer(String base, String currency1, String currency2) throws Exception {
        try {
            Call<Currency> server_call = apiService.getLatestRate(base, currency1, currency2);
            server_call.enqueue(new Callback<Currency>() {
                @Override
                public void onResponse(Call<Currency> call, Response<Currency> response) {
                    Currency currency = response.body();
                    currencyMutableLiveData.setValue(currency);
                }

                @Override
                public void onFailure(Call<Currency> call, Throwable t) {

                }
            });
        } catch (Exception exception) {
            Log.e(TAG, "Line no 54 and Exception is " + exception.getLocalizedMessage());
        }

    }

}
