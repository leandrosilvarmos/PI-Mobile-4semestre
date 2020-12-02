package com.example.dacfusion.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dacfusion.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin_user.setOnClickListener {
            val email = etEmail_user.text.toString().trim()
            val senha = etSenha_user.text.toString().trim()

            if(email.isEmpty()){
                etEmail_user.error =  "Email e obrigatorio"
                etEmail_user.requestFocus()
                return@setOnClickListener
            }

            if (senha.isEmpty()){
                etSenha_user.error =  "Senha é obrigatoria"
                etSenha_user.requestFocus()
                return@setOnClickListener
            }


            
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}