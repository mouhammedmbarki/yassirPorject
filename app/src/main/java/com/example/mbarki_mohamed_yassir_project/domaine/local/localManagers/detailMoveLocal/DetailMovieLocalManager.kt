package com.example.mbarki_mohamed_yassir_project.domaine.local.localManagers.detailMoveLocal

import com.example.mbarki_mohamed_yassir_project.domaine.local.dao.DetailMoveDao.DetailMovieDao
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.DetailMovie
import javax.inject.Inject

class DetailMovieLocalManager @Inject constructor(private val detailMovieDao: DetailMovieDao) {
    suspend fun insertDetailMovie(movies: DetailMovie){
        detailMovieDao.insert(movies)
    }

    suspend fun getMovieById(id:Int):DetailMovie = detailMovieDao.getMovieById(id)
    suspend fun deleteDetailMovieByID(id:Int){
        detailMovieDao.deleteDetailMovieByID(id)
    }

}