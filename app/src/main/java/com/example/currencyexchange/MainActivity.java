package com.example.currencyexchange;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.currencyexchange.model.Currency;
import com.example.currencyexchange.utils.CheckInternetConnection;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Currency currency;
    private EditText baseSymbol, symbol2, symbol3;
    private Button getRates;
    private TextView symbol2rate, symbol3rate, internetStatus;
    private ProgressDialog progressDialog;
    private String baseCurrency, exchangeCurrency1, exchangeCurrency2;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseSymbol = findViewById(R.id.symbol1);
        symbol2 = findViewById(R.id.symbol2);
        symbol3 = findViewById(R.id.symbol3);
        getRates = findViewById(R.id.submit);
        symbol2rate = findViewById(R.id.symbol2rate);
        symbol3rate = findViewById(R.id.symbol3rate);
        internetStatus = findViewById(R.id.internetStatus);
        showProgressDialog();
        getRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckInternetConnection.haveNetworkConnection(MainActivity.this)) {
                    try {
                        progressDialog.show();
                        MyApplicationClass.setMainActivityViewModel(MainActivity.this);
                        baseCurrency = baseSymbol.getText().toString();
                        exchangeCurrency1 = symbol2.getText().toString();
                        exchangeCurrency2 = symbol3.getText().toString();
                        LiveData<Currency> currencyLiveData = null;
                        currencyLiveData = MyApplicationClass.mainActivityViewModel.getPostsLinkInBioList(baseCurrency, exchangeCurrency1, exchangeCurrency2);
                        currencyLiveData.observe(MainActivity.this, new Observer<Currency>() {
                            @Override
                            public void onChanged(@Nullable Currency liveCurrencyExchangeRate) {
                                currency = liveCurrencyExchangeRate;
                                symbol2rate.setText(baseCurrency + "-" + exchangeCurrency1 + " " + currency.getRates().getCAD().toString());
                                symbol3rate.setText(baseCurrency + "-" + exchangeCurrency2 + " " + currency.getRates().getGBP().toString());
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                            }
                        });
                    } catch (Exception e) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        internetStatus.setText(getResources().getString(R.string.ERROR_API));
                        internetStatus.setVisibility(View.VISIBLE);
                    }

                } else {
                    internetStatus.setText(getResources().getString(R.string.CHECK_INTERNET));
                    internetStatus.setVisibility(View.VISIBLE);
                    CheckInternetConnection.noInternetConnection(MainActivity.this);

                }
            }

        });

    }

    /**
     * this will show the progress dialogue when we hit the server to get the data
     */
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getResources().getString(R.string.PLEASE_WAIT));
        progressDialog.setMessage(getResources().getString(R.string.LOADING_DATA));
        progressDialog.setCancelable(false);
    }
}
