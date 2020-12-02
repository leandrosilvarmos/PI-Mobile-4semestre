package com.example.dacfusion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.dacfusion.R
import com.example.dacfusion.model.Categorias
import com.example.dacfusion.model.Produtos
import com.example.dacfusion.services.CategoriasApi
import com.example.dacfusion.services.ProdutoApi
import kotlinx.android.synthetic.main.activity_categorias.*
import kotlinx.android.synthetic.main.activity_produtos.*
import kotlinx.android.synthetic.main.produtos_item.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.NumberFormat
import java.util.concurrent.TimeUnit

class CategoriasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)
    }


    override fun onResume() {
        super.onResume()
        getCategoria()
    }


    fun getCategoria(){

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    val retrofit =  Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service = retrofit.create(CategoriasApi::class.java)
    val call = service.getCategoria()
    val callback = object: Callback<List<Categorias>> {
        override fun onFailure(call: Call<List<Categorias>>, t: Throwable) {
            Toast.makeText(this@CategoriasActivity, "Api não funcionando" , Toast.LENGTH_LONG).show()
            Log.e("Categorias" , "getCategoria" , t)
        }

        override fun onResponse(call: Call<List<Categorias>>, response: Response<List<Categorias>>) {
            if (response.isSuccessful){
                val categorias = response.body()
                refreshUi(categorias)
            }else{
                Toast.makeText(this@CategoriasActivity, "Erro em atualizar categoria" , Toast.LENGTH_LONG).show()
                Log.e(response.code().toString(), response.errorBody().toString())

            }
        }
    }

        call.enqueue(callback)

}

    fun refreshUi(categorias: List<Categorias>?){

        categorias?.let {
            for(categoria in categorias){

                txtNome_Categoria.text = categoria.nome
                txtTipo_Categoria.text = categoria.tipo

            }
        }

    }
}