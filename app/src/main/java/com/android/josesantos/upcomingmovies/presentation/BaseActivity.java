package com.android.josesantos.upcomingmovies.presentation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Jose Santos on 07/12/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isThereInternetConnection()){
            showConnectionError();
        }
    }

    public boolean isThereInternetConnection() {
        boolean isConnected;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    public void showConnectionError() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setMessage("Sorry, there is no internet connection!");
        dialogBuilder
                .setPositiveButton("CONNECT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Attempt to enable Wifi
                                if (!isThereInternetConnection()){
                                    final Intent wifiIntent =
                                            new Intent(Settings.ACTION_WIFI_SETTINGS);
                                    wifiIntent.addFlags(
                                            Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                                    startActivityForResult(wifiIntent,201);
                                }

                                dialog.dismiss();
                            }
                        }
                )
        .setNegativeButton("TRY AGAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onRetryConnection();
                dialogInterface.dismiss();
            }
        });

        dialogBuilder.create().show();
    }

    public void showUnkownError() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setMessage("Something went wrong, want to try again?");
        dialogBuilder
                .setPositiveButton("TRY AGAIN",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                onRetryConnection();
                                dialog.dismiss();
                            }
                        }
                );

        dialogBuilder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: "+requestCode+" resultCode "+resultCode);
        if (requestCode == 201){
           onRetryConnection();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    abstract void onRetryConnection();
}
