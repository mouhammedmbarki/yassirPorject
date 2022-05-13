package com.example.mbarki_mohamed_yassir_project.presentation.DetailMovie.intent

sealed class DetailMovieIntent{
    data class loadMovie(val moveId:Int):DetailMovieIntent()
}
