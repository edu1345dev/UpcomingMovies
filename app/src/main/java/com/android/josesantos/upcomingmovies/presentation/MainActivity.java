package com.android.josesantos.upcomingmovies.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.josesantos.upcomingmovies.AppApplication;
import com.android.josesantos.upcomingmovies.R;
import com.android.josesantos.upcomingmovies.model.usecase.GetUpcommingMoviesList;
import com.android.josesantos.upcomingmovies.model.usecase.LoadUpcommingMovieList;

import javax.inject.Inject;

/**
 * Created by josesantos on 04/12/17.
 */

public class MainActivity extends AppCompatActivity{

    @Inject
    LoadUpcommingMovieList loadUpcommingMovieList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        AppApplication.getAppComponent().inject(this);

        Button button = findViewById(R.id.bt_load);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LoadUpcommingMovieList upcommingMovieList =
//                        new LoadUpcommingMovieList();
//                upcommingMovieList.execute();

                loadUpcommingMovieList.execute();
            }
        });
    }
}
