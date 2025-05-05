package com.example.lab2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.lab2.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesAdapter = MoviesAdapter(moviesViewModel.listOfMovies)
        binding.moviesRecyclerView.adapter = moviesAdapter

        moviesViewModel.dataUpdated.observe(this, Observer { updated ->
            if (updated) {
                moviesAdapter = MoviesAdapter(moviesViewModel.listOfMovies)
                binding.moviesRecyclerView.adapter = moviesAdapter
            }
        })

        binding.saveButton.setOnClickListener {
            val movieName = binding.movieNameEditText.text.toString()
            val genre = binding.genreEditText.text.toString()

            if (movieName.isNotEmpty() && genre.isNotEmpty()) {
                val movie = Movie(movieName, genre)
                moviesViewModel.addMovie(movie)
                binding.movieNameEditText.text.clear()
                binding.genreEditText.text.clear()
            }
        }
    }
}
