package com.example.newsletter.data.service


import com.example.newsletter.models.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {
    //GET --> On lance une requête de type GET
    // everything est l'action du web service qu'on veut apeler
    // Elle sera concaténée avec l'url prédéfini dans retrofit
    @GET("/v2/everything")
    fun list(@Query("q") query: String): Call<ArticleResponse>
    @GET("/v2/top-headlines")
    fun category(@Query("category") query: String): Call<ArticleResponse>
}