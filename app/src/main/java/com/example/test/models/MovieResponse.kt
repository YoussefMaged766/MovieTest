package com.example.test.models

data class MovieResponse(
    val dates: Dates?=null,
    val page: Int?=null,
    val results: List<MovieItem>?=null,
    val total_pages: Int?=null,
    val total_results: Int?=null
)
data class Dates(
    val maximum: String,
    val minimum: String
)

data class MovieItem(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)