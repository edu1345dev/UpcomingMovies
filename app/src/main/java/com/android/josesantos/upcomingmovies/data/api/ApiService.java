package com.android.josesantos.upcomingmovies.data.api;

import com.android.josesantos.upcomingmovies.data.api.constants.ApiConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by josesantos on 03/12/17.
 */

public class ApiService {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final int TIMEOUT_SECONDS = 30;
    private static final String PARAM_API_KEY = "api_key";

    protected static <S> S createService(Class<S> serviceClass) {
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        return builder.client(httpClient).build();
    }

    private static OkHttpClient getHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new ApiKeyInterceptor());

        builder.connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        builder.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        builder.writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);

        return builder.build();
    }

    //add API KEY to each request
    public static class ApiKeyInterceptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            HttpUrl originalUrl = originalRequest.url();

            HttpUrl newUrl = originalUrl.newBuilder()
                    .addQueryParameter(PARAM_API_KEY, ApiConstants.API_KEY)
                    .build();

            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .url(newUrl);

            Request request = requestBuilder.build();

            return chain.proceed(request);
        }
    }
}
