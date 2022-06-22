package com.example.projetocep;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;



public class RetrofitConfigAxiosPost {

    Retrofit retrofitAxios = new Retrofit.Builder()
            .baseUrl("http://djzefx.ddns.net:3002/api/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    public AxiosService getAxiosService() {
        return this.retrofitAxios.create(AxiosService.class);
    }

}
