package com.example.dacfusion.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.dacfusion.R
import com.example.dacfusion.model.Produtos
import com.example.dacfusion.services.ProdutoApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_produtos.*
import kotlinx.android.synthetic.main.produtos_item.view.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.text.NumberFormat
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory



class produtosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produtos)

    }

    override fun onResume() {
        super.onResume()
        getProdutos()
    }


    fun getProdutos(){

        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

    val retrofit =  Retrofit.Builder()
        .baseUrl("http://192.168.2.11:8000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service = retrofit.create(ProdutoApi::class.java)
    val call = service.getProdutos()
    val callback = object: Callback<List<Produtos>>{
        override fun onFailure(call: Call<List<Produtos>>, t: Throwable) {
            Toast.makeText(this@produtosActivity, "Api não funcionando" , Toast.LENGTH_LONG).show()
            Log.e("Produtos" , "getProdutos" , t)
        }

        override fun onResponse(call: Call<List<Produtos>>, response: Response<List<Produtos>>) {
            if (response.isSuccessful){
                val produtos = response.body()
                refreshUi(produtos)
            }else{
                Toast.makeText(this@produtosActivity, "Erro em atualizar produto" , Toast.LENGTH_LONG).show()
                Log.e(response.code().toString(), response.errorBody().toString())

            }
        }
    }
        call.enqueue(callback)

    }



    fun refreshUi(produtos: List<Produtos>?){

        val formatter =  NumberFormat.getCurrencyInstance()
        produtos?.let {
            for(produto in produtos){
                val cardView = layoutInflater.inflate(R.layout.produtos_item, containerProdutos , false)

                Picasso.get().load("http://192.168.2.11:8000/storage/${produto.image}")
                    .error(R.drawable.imagedefault)
                    .into(cardView.image_produto)

                cardView.txt_tituloProduto.text = produto.nome
                cardView.txt_precoProduto.text =  formatter.format(produto.preco)
                cardView.txt_modeloProduto.text = produto.modelo
                containerProdutos.addView(cardView)

            }
        }

    }
}