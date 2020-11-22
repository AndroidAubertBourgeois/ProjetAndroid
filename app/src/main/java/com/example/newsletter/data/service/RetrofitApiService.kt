package com.example.newsletter.data.service


import com.example.newsletter.models.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApiService {
    //GET --> On lance une requête de type GET
    // everything est l'action du web service qu'on veut apeler
    // Elle sera concaténée avec l'url prédéfini dans retrofit
    @GET("/v2/everything")
    fun list(): Call<ArticleResponse>
    @GET("/v2/top-headlines")
    fun category(): Call<ArticleResponse>
    @GET("/v2/top-headlines")
    fun source(): Call<ArticleResponse>

}