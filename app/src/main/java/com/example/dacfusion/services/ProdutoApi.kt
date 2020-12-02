package com.example.dacfusion.services

import com.example.dacfusion.model.Produtos
import retrofit2.Call
import retrofit2.http.GET

interface ProdutoApi {

    @GET("produtos")
    fun getProdutos():Call<List<Produtos>>
}
