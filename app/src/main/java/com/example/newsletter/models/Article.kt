package com.example.newsletter.models

import java.util.*


data class Article(
        var id : String,
        val author:String,
        val title: String,
        val description:String,
        val content: String,
        val source: Source,
        val url: String,
        val urlToImage: String,
        val publishedAt: Date,
        var favorite: Boolean=false
)