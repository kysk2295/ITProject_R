package com.example.itproject

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit{

    fun getWordRetrofit():RetrofitService{

        val retrofit=
            Retrofit.Builder()
                .baseUrl("https://twinword-word-graph-dictionary.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(RetrofitService::class.java)
    }

}
