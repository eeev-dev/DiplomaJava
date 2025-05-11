package com.example.diplamajava.data.openai;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static OpenAiApi api;

    private RetrofitInstance() {}

    private static final class RetrofitHolder {
        static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getInstance() {
        return RetrofitInstance.RetrofitHolder.retrofit;
    }

    // Инициализация API
    public static OpenAiApi getApiInstance() {
        if (api == null) {
            Retrofit retrofit = RetrofitInstance.getInstance();
            api = retrofit.create(OpenAiApi.class);
        }
        return api;
    }
}
