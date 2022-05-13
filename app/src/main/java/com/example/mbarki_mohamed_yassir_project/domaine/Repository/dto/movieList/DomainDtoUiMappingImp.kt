package com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList

import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.base.DomainDTOMappingService
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.Movie
import com.example.mbarki_mohamed_yassir_project.utils.AppConstant.rootImageUrl
import javax.inject.Inject

class DomainDtoUiMappingImp @Inject constructor() :
    DomainDTOMappingService<Movie, UIMovieListModel> {
    override fun mapDomainToDTO(domain: Movie): UIMovieListModel {
        var imagePath=""
        domain.backdrop_path?.let {
            imagePath = rootImageUrl + it
        }
        return UIMovieListModel(imagePath, domain.title, domain.release_date,domain.id)
    }

}