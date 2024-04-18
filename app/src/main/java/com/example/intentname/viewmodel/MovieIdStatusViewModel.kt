package com.example.intentname.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieIdStatusViewModel : ViewModel() {
    private val _movieId = MutableLiveData<String>()

    val movieId : LiveData<String>
        get() = _movieId

    fun setMovieId(movieId: String) {
        _movieId.postValue(movieId)
    }
}