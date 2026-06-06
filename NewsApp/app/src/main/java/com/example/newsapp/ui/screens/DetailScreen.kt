package com.example.newsapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.data.model.Article

@Composable
fun DetailScreen(
    article: Article
) {
    Column {
        AsyncImage(
            model = article.urlToImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Text(
            article.title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            article.description ?: "",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            article.content ?: "",
            modifier = Modifier.padding(16.dp)
        )
    }
}
