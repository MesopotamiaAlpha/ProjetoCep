package com.example.projetocep;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfigAxiosPost {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.25.106.222:3002/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    public AxiosService getAxiosService() {
        return this.retrofit.create(AxiosService.class);
    }
}
