package com.example.currencyexchange;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import com.example.currencyexchange.viewmodel.MainActivityViewModel;

/**
 * this is the parent Class of the Application which loads all the prerequisites for the Application
 */
public class MyApplicationClass extends Application {

    public static MainActivityViewModel mainActivityViewModel;

    @Override
    public void onCreate() {
        super.onCreate();
        mainActivityViewModel = new MainActivityViewModel(this);
    }

    /**
     * this method will initialize the ViewModel class by passing the context of the AppCompatActivity
     *
     * @param appCompatActivity
     */
    public static void setMainActivityViewModel(AppCompatActivity appCompatActivity) {
        mainActivityViewModel = ViewModelProviders.of(appCompatActivity).get(MainActivityViewModel.class);
    }
}
