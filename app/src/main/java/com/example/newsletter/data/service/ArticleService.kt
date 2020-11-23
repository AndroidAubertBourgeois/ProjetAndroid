package com.example.newsletter.data.service

import android.media.Image
import android.widget.Button
import com.example.newsletter.models.Article
import com.example.newsletter.models.ArticleResponse
import retrofit2.http.Url
import java.util.*

interface ArticleService {
    fun getArticles(sujet: String): ArticleResponse
}