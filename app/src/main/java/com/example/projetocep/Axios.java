package com.example.projetocep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Neste campo a baixo você esta dizendo para o Jackson ignorar estes dois campos do Json
@JsonIgnoreProperties({})


public class Axios {

    private String cepAxios;
    private String idAxios;

    public String getCepAxios() {
        return cepAxios;
    }

    public void setCepAxios(String cep) {
        this.idAxios = cep;
    }

    @Override
    public String toString() {
        return "CEP: " + getCepAxios()
                + "\nId: " + getCepAxios()
                + "\nCep: " + getCepAxios()
                ;
    }
}


