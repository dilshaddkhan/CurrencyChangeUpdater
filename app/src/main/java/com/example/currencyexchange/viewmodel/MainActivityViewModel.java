package com.example.currencyexchange.viewmodel;

import android.app.Application;
import android.app.ProgressDialog;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.currencyexchange.model.Currency;
import com.example.currencyexchange.repositories.CurrencyDetailListRepositories;


public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Currency> currencyMutableLiveData = new MutableLiveData<>();
    private CurrencyDetailListRepositories currencyDetailListRepositories;


    /*
    constructor to initialize the require values
     */
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    /**
     * this will return the object of the CurrencyDetailListRepositories class
     */
    private void init() {
        currencyDetailListRepositories = CurrencyDetailListRepositories.getInstance();
    }


    /**
     * this will return the updated list of rates from the repository by hitting the web
     * @return
     */
    public LiveData<Currency> getPostsLinkInBioList(String base,String currency1,String currency2) throws Exception {
        currencyMutableLiveData = currencyDetailListRepositories.getLinkInBioPosts(base,currency1,currency2);
        return currencyMutableLiveData;
    }

}


