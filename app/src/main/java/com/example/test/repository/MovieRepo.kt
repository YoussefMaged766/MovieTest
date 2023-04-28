package com.example.test.repository

import com.example.test.utils.Status
import com.example.test.utils.WebServices
import kotlinx.coroutines.flow.flow

class MovieRepo(private val webServices: WebServices) {

    suspend fun getUpComingMovies(page:Int) = flow {
     emit(Status.Loading)
        try {
            val response = webServices.getUpComingMovies(page)
            emit(Status.Success(response))
        } catch (e: Exception) {
            emit(Status.Error(e.message.toString()))
        }

    }
}