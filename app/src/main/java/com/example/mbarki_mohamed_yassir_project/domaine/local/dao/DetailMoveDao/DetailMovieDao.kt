package com.example.mbarki_mohamed_yassir_project.domaine.local.dao.DetailMoveDao

import androidx.room.Dao
import androidx.room.Query
import com.example.mbarki_mohamed_yassir_project.domaine.local.dao.baseDAO.BaseDao
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.DetailMovie
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.Movie

@Dao
interface DetailMovieDao:BaseDao<DetailMovie> {

    @Query("SELECT * FROM DetailMovie WHERE id =:id ")
    suspend fun getMovieById(id:Int):DetailMovie

    @Query("DELETE FROM DetailMovie WHERE id =:id")
    suspend fun deleteDetailMovieByID(id:Int)
}