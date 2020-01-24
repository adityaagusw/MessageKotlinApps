package com.adityaagusw.messagekotlinapps.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.adityaagusw.messagekotlinapps.BackEnd.ApiClient
import com.adityaagusw.messagekotlinapps.Model.DefaultResponse
import com.adityaagusw.messagekotlinapps.Model.LoginResponse
import com.adityaagusw.messagekotlinapps.Model.User
import com.adityaagusw.messagekotlinapps.R
import com.adityaagusw.messagekotlinapps.Storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        edtNamaUpdate.setText(SharedPrefManager.getInstance(this).user.nama)
        edtEmailUpdate.setText(SharedPrefManager.getInstance(this).user.email)

        btnUpdate.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnUpdate->{
                updateUser()
            }
        }
    }

    private fun updateUser(){

        val nama = edtNamaUpdate.text.toString().trim()
        val email = edtEmailUpdate.text.toString().trim()

        if (nama.isEmpty() || email.isEmpty()){
            Toast.makeText(applicationContext, "Nama or Email Masih Kosong", Toast.LENGTH_SHORT).show()
        }else{

            ApiClient.instance.updateUser(SharedPrefManager.getInstance(this).user.id,
                                            nama, email)
                .enqueue(object : Callback<LoginResponse>{
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                        if (!response.body()?.error!!){
                            SharedPrefManager.getInstance(this@ProfileActivity).saveUser(response.body()!!.user)
                        }

                    }

                })

        }

    }


}
