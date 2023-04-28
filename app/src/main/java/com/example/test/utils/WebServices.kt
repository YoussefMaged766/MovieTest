package com.example.test.utils

import com.example.test.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("page") page: Int
    ): MovieResponse

}