package com.adityaagusw.messagekotlinapps.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.adityaagusw.messagekotlinapps.BackEnd.ApiClient
import com.adityaagusw.messagekotlinapps.Model.DefaultResponse
import com.adityaagusw.messagekotlinapps.R
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {

            val email = edtEmailReg.text.toString().trim()
            val password = edtPasswordReg.text.toString().trim()
            val nama = edtNamaReg.text.toString().trim()
            val alamat = edtAlamatReg.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || nama.isEmpty() || alamat.isEmpty()){
                Toast.makeText(applicationContext, "Kosong !", Toast.LENGTH_SHORT).show()
            }else{
                registUser()
            }


        }

    }

    private fun registUser() {

        val email = edtEmailReg.text.toString().trim()
        val password = edtPasswordReg.text.toString().trim()
        val nama = edtNamaReg.text.toString().trim()
        val alamat = edtAlamatReg.text.toString().trim()

        ApiClient.instance.createUser(email, password, nama, alamat)
            .enqueue(object : Callback<DefaultResponse>{
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    Toast.makeText(applicationContext, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    finish()
                }

            })

    }
}
