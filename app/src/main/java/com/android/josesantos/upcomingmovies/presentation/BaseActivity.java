package com.android.josesantos.upcomingmovies.presentation;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.android.josesantos.upcomingmovies.R;

/**
 * Created by Jose Santos on 07/12/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    private static final int REQUEST_CODE = 201;
    private AlertDialog dialogConnetionError;
    private AlertDialog dialogUnkownError;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!isThereInternetConnection()){
//            showConnectionError();
//        }
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
        if (isDialogConnetionErrorShowing()){
            return;
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setMessage(R.string.no_internet_connection);
        dialogBuilder
                .setPositiveButton(R.string.action_connect,
                        (dialog, whichButton) -> {
                            // Attempt to enable Wifi
                            if (!isThereInternetConnection()){
                                final Intent wifiIntent =
                                        new Intent(Settings.ACTION_WIFI_SETTINGS);
                                wifiIntent.addFlags(
                                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                                startActivityForResult(wifiIntent,REQUEST_CODE);
                            }

                            dialog.dismiss();
                        }
                )
        .setNegativeButton(R.string.action_try_again, (dialogInterface, i) -> {
            onRetryConnection();
            dialogInterface.dismiss();
        });

        dialogConnetionError = dialogBuilder.create();
        dialogConnetionError.show();
    }

    private boolean isDialogConnetionErrorShowing() {
        return dialogConnetionError != null && dialogConnetionError.isShowing();
    }

    public void showUnkownError() {
        if (isDialogUnknownErrorShowing()){
            return;
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setMessage(R.string.unknown_error);
        dialogBuilder
                .setPositiveButton(R.string.action_try_again,
                        (dialog, whichButton) -> {
                            onRetryConnection();
                            dialog.dismiss();
                        }
                );

        dialogUnkownError = dialogBuilder.create();
        dialogUnkownError.show();
    }

    private boolean isDialogUnknownErrorShowing() {
        return dialogUnkownError != null && dialogUnkownError.isShowing();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE){
           onRetryConnection();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    abstract void onRetryConnection();
}
