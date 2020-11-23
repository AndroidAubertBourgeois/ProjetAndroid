package com.example.newsletter.data

import com.example.newsletter.data.service.CategoryOnlineService
import com.example.newsletter.models.Article
import com.example.newsletter.models.ArticleResponse

class CategoryRepository {
    private val apiService: CategoryOnlineService

    init {
        apiService = CategoryOnlineService()
    }

    fun getArticles(subject: String): ArticleResponse = apiService.getArticles(subject)


    companion object {
        private var instance: CategoryRepository? = null
        fun getInstance(): CategoryRepository {
            if (instance == null) {
                instance = CategoryRepository()
            }
            return instance!!
        }
    }
}