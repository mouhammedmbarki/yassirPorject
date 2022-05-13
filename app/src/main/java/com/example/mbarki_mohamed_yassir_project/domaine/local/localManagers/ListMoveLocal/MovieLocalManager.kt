package com.example.mbarki_mohamed_yassir_project.domaine.local.localManagers.ListMoveLocal

import com.example.mbarki_mohamed_yassir_project.domaine.local.dao.listMoveDao.MovieDao
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.Movie
import javax.inject.Inject

class MovieLocalManager @Inject constructor(private val movieDao: MovieDao) {
    suspend fun insertAllMovie(movies:List<Movie>){
        movieDao.insertList(movies)
    }

    suspend fun getAllMovie():List<Movie> = movieDao.getAllMovie()

    suspend fun detetAllMovie (){
        movieDao.deleteAllMovie()
    }
}