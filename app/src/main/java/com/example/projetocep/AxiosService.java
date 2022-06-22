package com.example.projetocep;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AxiosService {
    @POST("{cep}")
    @FormUrlEncoded
    static Call<Axios> postAxios(@Field("cep") CEP cep) {
        return null;
    }

}
