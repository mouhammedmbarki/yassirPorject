package com.example.mbarki_mohamed_yassir_project.domaine.Repository.listMovie

import com.easymountain.mhikesvoyage.utils.intentState.DataState
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList.UIMovieListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ListMovieRepositoryImpl {
   suspend fun loadAllMovieList():Flow<DataState<List<UIMovieListModel>>>
}