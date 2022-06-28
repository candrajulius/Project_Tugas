package com.candra.project_tugas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.candra.project_tugas.R
import com.candra.project_tugas.databinding.ActivitySignUpBinding

class SignUp: AppCompatActivity(),View.OnClickListener
{
    private lateinit var binding: ActivitySignUpBinding
    private var emailValid = false
    private var nameValid = false
    private var confirmationPasswordValid = false
    private var passwordValid = false
    private var phoneValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDaftar.setOnClickListener(this)
        binding.haveAccount.setOnClickListener(this)

        supportActionBar?.title = "Daftar"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        validateButton()

        setComponentView()

    }

    private fun validateButton(){
        with(binding) {
            btnDaftar.apply {
                if (nameValid && passwordValid && confirmationPasswordValid && emailValid && phoneValid) {
                    isEnabled = true
                    setBackgroundColor(ContextCompat.getColor(this@SignUp, R.color.purple_500))
                }else{
                    isEnabled = false
                    setBackgroundColor(ContextCompat.getColor(this@SignUp,android.R.color.darker_gray))
                }
            }
        }
    }

    private fun setComponentView(){
        with(binding){
            edtName.addTextChangedListener {
                val name = it.toString().trim().lowercase()
                validateName(name)
            }
            edtPassword.addTextChangedListener {
                val password = it.toString().trim().lowercase()
                validatePassword(password)
            }
            edtConfirmPassword.addTextChangedListener {
                val confirmPassword = it.toString().trim().lowercase()
                validateConfirmPassword(confirmPassword)
            }
            edtEmail.addTextChangedListener {
                val email = it.toString().trim().lowercase()
                validateEmail(email)
            }
            edtPhone.addTextChangedListener {
                val phone = it.toString().trim().lowercase()
                validatePhone(phone)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            startActivity(Intent(this@SignUp,Login::class.java))
                .also { finish() }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validateConfirmPassword(confirmPassword: String){
        val password = binding.edtPassword.text.toString().trim().lowercase()
        if (confirmPassword != password){
            confirmationPasswordValid = false
            showErrorToValidView(true,5)
        }else{
            confirmationPasswordValid = true
            showErrorToValidView(false,5)
        }
        validateButton()
    }

    private fun validatePhone(phone: String){
        if (phone.isEmpty()){
            phoneValid = false
            showErrorToValidView(true,3)
        }else{
            phoneValid = true
            showErrorToValidView(false,3)
        }
        validateButton()
    }

    private fun validateName(name: String){
        if(name.isEmpty()){
            nameValid = false
            showErrorToValidView(true,1)
        }else{
            nameValid = true
            showErrorToValidView(false,1)
        }
        validateButton()
    }

    private fun validatePassword(password: String){
        if (password.isEmpty()){
            passwordValid = false
            showErrorToValidView(true,4)
        }else{
            passwordValid = true
            showErrorToValidView(false,4)
        }
        validateButton()
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

    private fun showErrorToValidView(isNotvalid: Boolean,position: Int){
        binding.apply {
            when(position){
                1 -> edtName.error = if (isNotvalid) "nama tidak boleh kosong" else null
                2 -> edtEmail.error = if (isNotvalid) "Email tidak valid" else null
                3 -> edtPhone.error = if (isNotvalid) "Nomor handphone tidak boleh kosong" else null
                4 -> edtPassword.error = if(isNotvalid) "Password tidak boleh kosong " else null
                5 -> edtConfirmPassword.error = if(isNotvalid) getString(R.string.password_not_same) else null
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_daftar -> {
                toLogin()
            }
            R.id.have_account -> {
               toLogin()
            }
        }
    }

    private fun toLogin(){
        startActivity(Intent(this@SignUp,Login::class.java))
            .also { finish() }
    }

}