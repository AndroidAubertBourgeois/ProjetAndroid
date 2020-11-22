package com.example.newsletter.data

import com.example.newsletter.data.service.SourceOnlineService
import com.example.newsletter.models.Article

class SourceRepository {
    private val apiService: SourceOnlineService

    init {
        apiService = SourceOnlineService()
    }

    fun getArticles(): List<Article> = apiService.getArticles()


    companion object {
        private var instance: SourceRepository? = null
        fun getInstance(): SourceRepository {
            if (instance == null) {
                instance = SourceRepository()
            }
            return instance!!
        }
    }
}