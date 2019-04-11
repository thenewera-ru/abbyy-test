package com.example.abbyyfetchingapi.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(StackoverflowAPI.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
