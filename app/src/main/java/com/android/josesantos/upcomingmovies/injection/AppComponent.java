package com.android.josesantos.upcomingmovies.injection;

import com.android.josesantos.upcomingmovies.data.api.ApiService;

import dagger.Component;

/**
 * Created by josesantos on 03/12/17.
 */

@Component(modules={PresentationModule.class, DataModule.class, ApiServiceModule.class})
public interface AppComponent {

}
