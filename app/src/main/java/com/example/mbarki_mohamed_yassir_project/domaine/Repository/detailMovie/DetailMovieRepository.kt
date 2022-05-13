package com.example.mbarki_mohamed_yassir_project.domaine.Repository.detailMovie

import com.easymountain.mhikesvoyage.utils.intentState.DataState
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie.DomainDtoUiDetailMovieMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie.RemoteResponseDtoLocalDetailMoveMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie.UiDetailMovieModel
import com.example.mbarki_mohamed_yassir_project.domaine.local.localManagers.detailMoveLocal.DetailMovieLocalManager
import com.example.mbarki_mohamed_yassir_project.domaine.remote.detailMovie.manager.ApiDetailMovie
import com.example.mbarki_mohamed_yassir_project.utils.AppConstant
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class DetailMovieRepository constructor(
    private val apiDetailMovie: ApiDetailMovie,
    private val detailMovieLocalManager: DetailMovieLocalManager,
    private val domainDtoUiDetailMovieMappingImp: DomainDtoUiDetailMovieMappingImp,
    private val remoteResponseDtoLocalDetailMoveMappingImp: RemoteResponseDtoLocalDetailMoveMappingImp
) : DetailMovieImp {
    override suspend fun loadDetailMovie(id: Int): Flow<DataState<UiDetailMovieModel>> =
        callbackFlow {
            send(DataState.Loading)
            if (apiDetailMovie.getDetailMovieList(AppConstant.apiKey, id).isSuccessful) {
                apiDetailMovie.getDetailMovieList(AppConstant.apiKey, id).body()?.let {
                    var localData = async {
                        remoteResponseDtoLocalDetailMoveMappingImp.mapDomainToDTO(it)
                    }
                    var uiModel = async {
                        detailMovieLocalManager.deleteDetailMovieByID(id)
                        detailMovieLocalManager.insertDetailMovie(localData.await())
                        domainDtoUiDetailMovieMappingImp.mapDomainToDTO(
                            detailMovieLocalManager.getMovieById(
                                id
                            )
                        )
                    }
                    send(DataState.Success(uiModel.await()))

                } ?: run {
                    send(DataState.Error(Exception("null body")))
                }
            } else {
                send(
                    DataState.Error(
                        Exception(
                            apiDetailMovie.getDetailMovieList(
                                AppConstant.apiKey,
                                id
                            ).errorBody().toString()
                        )
                    )
                )

            }
            awaitClose()
        }
}