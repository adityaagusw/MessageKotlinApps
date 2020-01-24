package com.adityaagusw.messagekotlinapps.BackEnd

import com.adityaagusw.messagekotlinapps.Model.DefaultResponse
import com.adityaagusw.messagekotlinapps.Model.LoginResponse
import com.adityaagusw.messagekotlinapps.Model.MessageResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("createuser")
    fun createUser(@Field("email") email : String,
                   @Field("password") password : String,
                   @Field("nama") nama : String,
                   @Field("alamat") alamat : String
                   ) : Call<DefaultResponse>

    @FormUrlEncoded
    @POST("userlogin")
    fun userLogin(@Field("email") email :String,
                  @Field("password") password :String
                  ) : Call<LoginResponse>

    @FormUrlEncoded
    @POST("kirimpesan")
    fun kirimPesanUser(@Field("user_id") user_id : Int,
                       @Field("pesan") pesan : String
                       ) : Call<MessageResponse>

}