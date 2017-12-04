package com.android.josesantos.upcomingmovies.data.api;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by josesantos on 03/12/17.
 */

public class ApiService {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final int TIMEOUT_SECONDS = 30;

    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient.Builder httpClientBuilder = getHttpClient().newBuilder();

        //interceptor for logging requests
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClientBuilder.addInterceptor(loggingInterceptor);

        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        return getRetrofit(httpClientBuilder.build()).create(serviceClass);
    }

    public static Retrofit getRetrofit() {
        return getRetrofit(ApiService.getHttpClient());
    }

    private static Retrofit getRetrofit(OkHttpClient httpClient) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        return builder.client(httpClient).build();
    }

    private static OkHttpClient getHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        builder.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        builder.writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);

        return builder.build();
    }
}
