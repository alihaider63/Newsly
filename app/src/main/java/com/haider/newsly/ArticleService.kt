package com.haider.newsly

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/everything?q=apple&from=2022-03-05&to=2022-03-05&sortBy=popularity&apiKey=52a0ddc98f3a41868c997fa9573622f8

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "52a0ddc98f3a41868c997fa9573622f8"

interface ArticleInterface {
    //endpoint
    @GET("v2/everything?apiKey=$API_KEY")
    fun getArticles(@Query("q")q: String,
                    @Query("sortBy")sortBy: String,
                    @Query("page")page: Int) : Call<News>
}

object ArticleService {
    val articleInstance: ArticleInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        articleInstance = retrofit.create(ArticleInterface::class.java)
    }
}