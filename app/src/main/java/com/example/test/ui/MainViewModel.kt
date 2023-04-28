package com.example.test.ui

import androidx.lifecycle.ViewModel
import com.example.test.repository.MovieRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import com.example.test.models.MovieResponse
import com.example.test.utils.ApiManager
import com.example.test.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    private val movieRepo = MovieRepo(ApiManager.getWebServices())

    private val _stateSuccess = MutableStateFlow(MovieResponse())
    val stateSuccess = _stateSuccess.asStateFlow()

    private val _stateError = MutableStateFlow("")
    val stateError = _stateError.asStateFlow()

    private val _stateLoading = MutableStateFlow(true)
    val stateLoading = _stateLoading.asStateFlow()


    suspend fun getUpComingMovies(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        movieRepo.getUpComingMovies(page).collect {
            when (it) {
                is Status.Loading -> {
                    _stateLoading.value = true
                }

                is Status.Success -> {
                    _stateSuccess.value = it.data
                    _stateLoading.value = false
                }

                is Status.Error -> {
                    _stateError.value = it.message
                    _stateLoading.value = false
                }
            }
        }
    }

}