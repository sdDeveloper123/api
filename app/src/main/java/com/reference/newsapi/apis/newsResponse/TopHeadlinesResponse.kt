package com.reference.newsapi.apis.newsResponse

data class TopHeadlinesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)