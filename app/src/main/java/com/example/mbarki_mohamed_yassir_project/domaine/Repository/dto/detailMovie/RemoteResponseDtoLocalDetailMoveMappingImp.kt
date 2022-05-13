package com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie

import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.base.DomainDTOMappingService
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.DetailMovie
import com.example.mbarki_mohamed_yassir_project.domaine.remote.detailMovie.models.RemoteDetailMovieResponse
import javax.inject.Inject

class RemoteResponseDtoLocalDetailMoveMappingImp @Inject constructor() :
    DomainDTOMappingService<RemoteDetailMovieResponse, DetailMovie> {
    override fun mapDomainToDTO(domain: RemoteDetailMovieResponse): DetailMovie =
        DetailMovie(
            domain.id,
            domain.backdrop_path,
            domain.title,
            domain.release_date,
            domain.overview
        )

}