package com.example.mbarki_mohamed_yassir_project.presentation.listMovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easymountain.mhikesvoyage.utils.intentState.DataState
import com.example.mbarki_mohamed_yassir_project.di.IoDispatcher
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList.UIMovieListModel
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.listMovie.ListMovieRepositoryImpl
import com.example.mbarki_mohamed_yassir_project.presentation.listMovie.intent.ListMovieIntent
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
class ListMovieViewModel @Inject constructor(
    private val listMovieRepositoryImpl: ListMovieRepositoryImpl,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    ViewModel() {
    val listMovieIntent = Channel<ListMovieIntent>(Channel.UNLIMITED)
    private val _listMoveDataState: MutableStateFlow<DataState<List<UIMovieListModel>>> =
        MutableStateFlow(
            DataState.Idle
        )

    val listMoveDataState: StateFlow<DataState<List<UIMovieListModel>>> get() = _listMoveDataState

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch(Dispatchers.Default) {
            listMovieIntent.consumeAsFlow().collect {
                when (it) {
                    is ListMovieIntent.loadListMovies -> {
                        loadAllMovie()
                    }
                }
            }
        }
    }

    private suspend fun loadAllMovie() {
        viewModelScope.launch(ioDispatcher) {
            listMovieRepositoryImpl.loadAllMovieList().collect {
                _listMoveDataState.value = it
            }
        }
    }

}