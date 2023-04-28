package com.example.test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.test.adapter.MovieAdapter
import com.example.test.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {
     lateinit var binding: ActivityMainBinding
     private val adapter: MovieAdapter by lazy { MovieAdapter() }
     private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        collectStates()

    }

    private fun collectStates() {
        lifecycleScope.launchWhenStarted {
            viewModel.getUpComingMovies(1)

            viewModel.stateSuccess.collect {

                adapter.submitList(it.results)
                binding.rvMovies.adapter = adapter
                binding.rvMovies.visibility = View.VISIBLE
            }
        }
        lifecycleScope.launch {
            viewModel.stateError.collect {
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            }
        }
        lifecycleScope.launch {
            viewModel.stateLoading.collect {

                binding.progressBar.isVisible = it
            }
        }


    }

}