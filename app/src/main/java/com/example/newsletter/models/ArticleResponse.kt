package com.example.newsletter.models

data class ArticleResponse(
        val status: String,
        val totalResults: Int,
        val articles: List<Article>
    )