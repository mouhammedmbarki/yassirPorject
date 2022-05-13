package com.example.mbarki_mohamed_yassir_project.domaine.Repository.detailMovie

import com.easymountain.mhikesvoyage.utils.intentState.DataState
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie.UiDetailMovieModel
import kotlinx.coroutines.flow.Flow

interface DetailMovieImp {
    suspend fun loadDetailMovie(id:Int):Flow<DataState<UiDetailMovieModel>>
}