package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofit.data.PostRegisterResponse
import com.example.retrofit.data.RegisterRequest
import com.example.retrofit.databinding.ActivityRegisterBinding
import com.example.retrofit.network.ApiClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.apply {
            button.setOnClickListener{
                if(!editTextEmail.text.isNullOrEmpty() && !editTextPassword.text.isNullOrEmpty()){
                    registerAction(editTextEmail.text.toString(), editTextPassword.text.toString())
                } else {
                    Toast.makeText(
                        this@RegisterActivity, "Data tidak boleh kosong", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun registerAction(email: String, password: String){
        val request = RegisterRequest(
            email = email,
            password = password,
            role = "admin"
        )

        ApiClient.instance.postRegister(request)
            .enqueue(object :
                retrofit2.Callback<PostRegisterResponse>{
            override fun onResponse(
                call: Call<PostRegisterResponse>,
                response: Response<PostRegisterResponse>
            ) {
                val code = response.code()
                if (code == 201) {
                    Toast.makeText(
                        this@RegisterActivity, "Register Berhasil",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Email sudah tersedia",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
                override fun onFailure(call: Call<PostRegisterResponse>, t: Throwable){
                    Toast.makeText(this@RegisterActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
        })
    }


}