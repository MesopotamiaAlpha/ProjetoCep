package com.example.projetocep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AxiosService {
    @GET("{id}/json")
    Call<Axios> buscarCEP(@Path("id") String cep);
}
