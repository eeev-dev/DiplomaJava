package com.example.diplamajava.data.openai;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OpenAiApi {
    @POST("v1/chat/completions")
    Call<ChatResponse> chatCompletion(
            @Header("Authorization") String authHeader,
            @Body ChatRequest request
    );
}


