package com.example.intentname.presenter

import android.util.Log
import com.example.intentname.model.DetailMovie
import com.example.intentname.view.View

class MovieDetailPresenter {

    private val detailMovie: DetailMovie = DetailMovie()
    private var view: View? = null

    fun attachView(view: View) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun fetchData(requestMovieId: String) {
        detailMovie.id = requestMovieId

        view?.showMovieDetailData(detailMovie)
    }
}