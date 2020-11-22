package com.example.newsletter.data

import com.example.newsletter.data.service.CategoryOnlineService
import com.example.newsletter.models.Article

class CategoryRepository {

    private val apiService: CategoryOnlineService = CategoryOnlineService()

    fun getArticles(): List<Article> = apiService.getArticles()


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