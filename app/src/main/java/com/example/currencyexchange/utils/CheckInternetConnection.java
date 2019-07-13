package com.example.currencyexchange.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

import com.example.currencyexchange.R;

/**
 * @author dilshad
 * this class will perform some general operations
 */
public class CheckInternetConnection {


    /**
     * this method will check weather the internet is available on the device or not and
     * will return the result in form of boolean
     * @param ctx
     */
    public static boolean haveNetworkConnection(Context ctx){
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo networkInfo : netInfo){
            if (networkInfo.getTypeName().equalsIgnoreCase(ctx.getResources().getString(R.string.WIFI)))
                if (networkInfo.isConnected())
                    haveConnectedWifi = true;
            if (networkInfo.getTypeName().equalsIgnoreCase(ctx.getResources().getString(R.string.MOBILE)))
                if (networkInfo.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


    /**
     * this method will produce a alert dialogue on the screen when
     * user don't have internet connectivity while requesting API to the server.
     * @param ctx
     */
    public static void noInternetConnection(final Context ctx){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
        alertDialogBuilder.setMessage(ctx.getResources().getString(R.string.NO_INTERNET));
        alertDialogBuilder.setNeutralButton(ctx.getResources().getString(R.string.INTERNET_INFO), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
