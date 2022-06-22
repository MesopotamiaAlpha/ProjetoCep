package com.example.projetocep;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AxiosService {
    @POST("create")
    Call<Axios> postAxios(@Body CEP cep);

}
