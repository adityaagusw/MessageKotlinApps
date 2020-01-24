package com.adityaagusw.messagekotlinapps.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adityaagusw.messagekotlinapps.BackEnd.ApiClient
import com.adityaagusw.messagekotlinapps.Model.MessageResponse
import com.adityaagusw.messagekotlinapps.R
import com.adityaagusw.messagekotlinapps.Storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogout.setOnClickListener(this)
        btnKirimPesan.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnLogout->{
                SharedPrefManager.getInstance(this).clear()
                finish()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }
            R.id.btnKirimPesan->{
                userKirimPesan()
            }
        }
    }

    private fun userKirimPesan(){
        val pesan = edtPesan.text.toString().trim()

        if (pesan.isEmpty()){
            Toast.makeText(applicationContext, "Pesan Masih Kosong", Toast.LENGTH_SHORT).show()
        }else{

            ApiClient.instance.kirimPesanUser(SharedPrefManager.getInstance(this).user.id, pesan)
                .enqueue(object : Callback<MessageResponse>{
                    override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {

                        //here method
                        Toast.makeText(applicationContext, response.body()!!.message, Toast.LENGTH_SHORT).show()

                    }

                })

        }

    }

    override fun onStart() {
        super.onStart()

        if(!SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

}
