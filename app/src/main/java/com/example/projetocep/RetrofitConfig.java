package com.example.projetocep;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;



public class RetrofitConfig {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    public CEPService getCEPService() {
        return this.retrofit.create(CEPService.class);
    }
}
