package com.example.dacfusion.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import retrofit2.converter.gson.GsonConverterFactory
import java.text.NumberFormat
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProdutosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProdutosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_produtos, container, false)
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
        val callback = object: Callback<List<Produtos>> {
            override fun onFailure(call: Call<List<Produtos>>, t: Throwable) {
//                Toast.makeText(this@ProdutosFragment, "Api não funcionando" , Toast.LENGTH_LONG).show()
                Log.e("Produtos" , "getProdutos" , t)
            }

            override fun onResponse(call: Call<List<Produtos>>, response: Response<List<Produtos>>) {
                if (response.isSuccessful){
                    val produtos = response.body()
                    refreshUi(produtos)
                }else{
//                Toast.makeText(this@ProdutosFragment , "Erro em Atualizar Produto" , Toast.LENGTH_LONG).show()
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

    companion object {

        @JvmStatic
        fun newInstance() =  ProdutosFragment()
    }
}