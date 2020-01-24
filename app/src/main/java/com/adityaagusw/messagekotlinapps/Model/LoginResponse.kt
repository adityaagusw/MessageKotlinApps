package com.adityaagusw.messagekotlinapps.Model

data class LoginResponse(val error: Boolean,
                         val message:String,
                         val user: User)