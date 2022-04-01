package com.haider.newsly

data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
