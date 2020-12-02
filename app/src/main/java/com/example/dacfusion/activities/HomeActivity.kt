package com.example.dacfusion.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dacfusion.R
import com.example.dacfusion.fragments.HomeFragment
import com.example.dacfusion.fragments.ProdutosFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        

        val frag = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer , frag).commit()

        bottomNavigationView.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.inicio ->{
                    val frag = HomeFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameContainer , frag).commit()

                }
                R.id.produtos ->{
                    val frag =  ProdutosFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameContainer , frag).commit()
                }
                R.id.carrinho ->{

                }
                R.id.perfil ->{



                }
            }

            true
        }

    }
}