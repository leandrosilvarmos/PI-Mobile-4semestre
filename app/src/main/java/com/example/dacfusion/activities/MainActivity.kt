package com.example.dacfusion.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.dacfusion.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        supportActionBar?.hide()

       Handler().postDelayed({
           startActivity(Intent(this, LoginActivity::class.java))
           finish()
       },4000)
    }
}