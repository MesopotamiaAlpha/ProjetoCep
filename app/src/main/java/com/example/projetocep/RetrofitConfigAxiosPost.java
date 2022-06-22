package com.example.projetocep;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;



public class RetrofitConfigAxiosPost {

    Retrofit retrofitAxios = new Retrofit.Builder()
            .baseUrl("http://192.168.1.23:3002/api/create")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    public AxiosService getAxiosService() {
        return this.retrofitAxios.create(AxiosService.class);
    }

}
