package com.candra.project_tugas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.candra.project_tugas.R
import com.candra.project_tugas.databinding.ActivityLoginBinding
import com.candra.project_tugas.utils.Helper

class Login: AppCompatActivity()
{

    private var nameValid = false
    private var passwordValid = false
    private var emailValid = false

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validateButton()

        supportActionBar?.title = "Login"

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this@Login,MainActivity::class.java))
            finish()
        }

        binding.tvNotHaveAccount.setOnClickListener {
            startActivity(Intent(this@Login,SignUp::class.java))
            finish()
        }


        setComponentView()
    }


    private fun setComponentView(){
        with(binding){
            edtName.addTextChangedListener {
                validateName(it.toString().trim().lowercase())
            }
            edtPassword.addTextChangedListener {
                validatePassword(it.toString().trim().lowercase())
            }
            edtEmail.addTextChangedListener {
                validateEmail(it.toString().trim().lowercase())
            }
        }
    }

    private fun validateEmail(email: String){
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailValid = false
            showErrorToValidView(true,2)
        }else{
            emailValid = true
            showErrorToValidView(false,2)
        }
        validateButton()
    }

    private fun validatePassword(password: String){
        if (password.isEmpty()){
            passwordValid = false
            showErrorToValidView(true,3)
        }else{
            passwordValid = true
            showErrorToValidView(false,3)
        }
        validateButton()
    }

    private fun showErrorToValidView(isNotvalid: Boolean,position: Int){
        binding.apply {
            when(position){
                1 -> edtName.error = if (isNotvalid) "nama tidak boleh kosong" else null
                2 -> edtEmail.error = if (isNotvalid) "Email tidak valid" else null
                3 -> edtPassword.error = if(isNotvalid) "Password tidak boleh kosong " else null
            }
        }
    }

    private fun validateName(name: String) {
        if (name.isEmpty()){
            nameValid = false
            showErrorToValidView(true,1)
        }else{
            nameValid = true
            showErrorToValidView(false,1)
        }
        validateButton()
    }

    private fun validateButton(){
        with(binding) {
            btnLogin.apply {
                if (nameValid && passwordValid && emailValid) {
                    isEnabled = true
                    setBackgroundColor(ContextCompat.getColor(this@Login, R.color.purple_500))
                }else{
                    isEnabled = false
                    setBackgroundColor(ContextCompat.getColor(this@Login,android.R.color.darker_gray))
                }
            }
        }
    }
}