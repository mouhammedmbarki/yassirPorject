package com.example.mbarki_mohamed_yassir_project.domaine.Repository.listMovie

import com.easymountain.mhikesvoyage.utils.intentState.DataState
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList.DomainDtoUiMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList.RemoteResponseDtoLocalMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList.UIMovieListModel
import com.example.mbarki_mohamed_yassir_project.domaine.local.localManagers.ListMoveLocal.MovieLocalManager
import com.example.mbarki_mohamed_yassir_project.domaine.remote.ListMovieRemote.manager.ApiMovieServices
import com.example.mbarki_mohamed_yassir_project.utils.AppConstant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ListMovieRepository constructor(
    private val apiMovieServices: ApiMovieServices,
    private val movieLocalManager: MovieLocalManager,
    private val domainDtoUiMappingImp: DomainDtoUiMappingImp,
    private val remoteResponseDtoLocalMappingImp: RemoteResponseDtoLocalMappingImp,
    private val coroutineDispatcher: CoroutineDispatcher
) : ListMovieRepositoryImpl {

    override suspend fun loadAllMovieList(): Flow<DataState<List<UIMovieListModel>>> =
        callbackFlow {
                if (apiMovieServices.getMovieList(apiKey = AppConstant.apiKey).isSuccessful) {
                    apiMovieServices.getMovieList(apiKey = AppConstant.apiKey).body()?.results?.let {
                        val localMovie = async(coroutineDispatcher) {
                            remoteResponseDtoLocalMappingImp.mapDomainToDTO(it)
                        }
                        val uiMovie = async(coroutineDispatcher) {
                            movieLocalManager.detetAllMovie()
                            movieLocalManager.insertAllMovie(localMovie.await())
                            domainDtoUiMappingImp.mapDomainToDTO(movieLocalManager.getAllMovie())
                        }
                        send(DataState.Success(uiMovie.await()))
                    } ?: kotlin.run {
                        send(DataState.Error(Exception("Null Body")))
                    }
                } else {
                    send(
                        DataState.Error(
                            Exception(
                                apiMovieServices.getMovieList(apiKey = AppConstant.apiKey)
                                    .errorBody().toString()
                            )
                        )
                    )
                }

            awaitClose()
        }
}