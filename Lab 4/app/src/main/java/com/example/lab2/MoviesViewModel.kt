package com.example.lab2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {
    private val _listOfMovies = mutableListOf<Movie>()
    private val _dataUpdated = MutableLiveData<Boolean>()

    val dataUpdated: LiveData<Boolean> get() = _dataUpdated
    val listOfMovies: List<Movie> get() = _listOfMovies

    fun addMovie(movie: Movie) {
        _listOfMovies.add(movie)
        _dataUpdated.value = true
    }
}
