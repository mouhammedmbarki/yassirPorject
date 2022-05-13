package com.example.mbarki_mohamed_yassir_project.presentation.DetailMovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easymountain.mhikesvoyage.utils.intentState.DataState
import com.example.mbarki_mohamed_yassir_project.di.IoDispatcher
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.detailMovie.DetailMovieImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie.UiDetailMovieModel
import com.example.mbarki_mohamed_yassir_project.presentation.DetailMovie.intent.DetailMovieIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val detailMovieImp: DetailMovieImp,
     @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    val detailMovieIntent = Channel<DetailMovieIntent>(Channel.UNLIMITED)
    private val _detailMoveDataState: MutableStateFlow<DataState<UiDetailMovieModel>> =
        MutableStateFlow(
            DataState.Idle
        )

    val detailMoveDataState: StateFlow<DataState<UiDetailMovieModel>> get() = _detailMoveDataState

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch(Dispatchers.Default) {
            detailMovieIntent.consumeAsFlow().collect {
                when (it) {
                    is DetailMovieIntent.loadMovie -> {
                        loadMovie(it.moveId)
                    }
                }
            }
        }
    }

    private suspend fun loadMovie(id: Int) {
        viewModelScope.launch(ioDispatcher) {
            detailMovieImp.loadDetailMovie(id).collect {
                _detailMoveDataState.value = it
            }
        }
    }
}