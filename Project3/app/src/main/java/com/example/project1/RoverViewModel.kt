package com.example.project1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RoverViewModel : ViewModel() {
    private val repository = RoverRepository()

    private val _rovers = MutableLiveData<List<Rover>?>(null)
    val rovers: LiveData<List<Rover>?> = _rovers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchRovers() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val roverList = repository.getRovers()
            _rovers.postValue(roverList)
            _isLoading.postValue(false)
        }
    }
}
