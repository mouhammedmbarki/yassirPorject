package com.example.mbarki_mohamed_yassir_project.di

import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.base.DomainDTOMappingService
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie.DomainDtoUiDetailMovieMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie.RemoteResponseDtoLocalDetailMoveMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie.UiDetailMovieModel
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList.DomainDtoUiMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList.RemoteResponseDtoLocalMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList.UIMovieListModel
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.DetailMovie
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.Movie
import com.example.mbarki_mohamed_yassir_project.domaine.remote.ListMovieRemote.model.RemoteMovieModel
import com.example.mbarki_mohamed_yassir_project.domaine.remote.detailMovie.models.RemoteDetailMovieResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDtoModule {

    @Singleton
    @Binds
    abstract fun provideUserFeedBackDomainDTOMappingGRPCImpl(remoteResponseDtoMappingImp: DomainDtoUiMappingImp): DomainDTOMappingService<Movie, UIMovieListModel>

    @Singleton
    @Binds
    abstract fun provideRemoteResponseDtoLocalMappingImp(remoteResponseDtoLocalMappingImp: RemoteResponseDtoLocalMappingImp): DomainDTOMappingService<RemoteMovieModel, Movie>

    @Singleton
    @Binds
    abstract fun provideDomainDtoUiDetailMovieMappingImp(domainDtoUiDetailMovieMappingImp: DomainDtoUiDetailMovieMappingImp): DomainDTOMappingService<DetailMovie, UiDetailMovieModel>

    @Singleton
    @Binds
    abstract fun provideRemoteResponseDtoLocalDetailMoveMappingImp(
        remoteResponseDtoLocalDetailMoveMappingImp: RemoteResponseDtoLocalDetailMoveMappingImp
    ): DomainDTOMappingService<RemoteDetailMovieResponse, DetailMovie>

}