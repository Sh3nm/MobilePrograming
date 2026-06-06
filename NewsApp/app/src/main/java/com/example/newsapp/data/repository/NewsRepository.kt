package com.example.newsapp.data.repository

import com.example.newsapp.data.api.RetrofitClient

class NewsRepository {
    suspend fun getNews() =
        RetrofitClient.apiService
            .getTopHeadlines(
                apiKey = "97089453a894443894fd8fcdc3ef6e71"
            )
}
