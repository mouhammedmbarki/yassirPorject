package com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList

import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.base.DomainDTOMappingService
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.Movie
import com.example.mbarki_mohamed_yassir_project.domaine.remote.ListMovieRemote.model.RemoteMovieModel
import javax.inject.Inject

class RemoteResponseDtoLocalMappingImp @Inject constructor() :
    DomainDTOMappingService<RemoteMovieModel, Movie> {
    override fun mapDomainToDTO(domain: RemoteMovieModel): Movie = Movie(
        domain.id,
        domain.poster_path,
        domain.adult,
        domain.overview,
        domain.release_date,
        domain.genre_ids,
        domain.original_title,
        domain.original_language,
        domain.title,
        domain.backdrop_path,
        domain.popularity,
        domain.vote_count,
        domain.video,
        domain.vote_average
    )
}