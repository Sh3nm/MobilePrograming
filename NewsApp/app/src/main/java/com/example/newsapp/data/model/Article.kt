package com.example.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class Article(
    val title: String,
    val description: String?,
    val content: String?,
    val author: String?,
    val urlToImage: String?,
    val publishedAt: String
)
