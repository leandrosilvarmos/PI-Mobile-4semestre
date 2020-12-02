package com.example.dacfusion.services

import com.example.dacfusion.model.Categorias
import retrofit2.Call
import retrofit2.http.GET

interface CategoriasApi {

    @GET("categoria")
    fun getCategoria():Call<List<Categorias>>

}