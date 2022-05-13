package com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie

import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.base.DomainDTOMappingService
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.DetailMovie
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.Movie
import com.example.mbarki_mohamed_yassir_project.utils.AppConstant.rootImageUrl
import javax.inject.Inject

class DomainDtoUiDetailMovieMappingImp @Inject constructor() :
    DomainDTOMappingService<DetailMovie, UiDetailMovieModel> {
    override fun mapDomainToDTO(domain: DetailMovie): UiDetailMovieModel {
        var imagePath=""
        domain.movieUrl?.let {
            imagePath = rootImageUrl + it
        }
        return UiDetailMovieModel(imagePath,domain.Title,domain.dateRelease,domain.overview)
    }


}