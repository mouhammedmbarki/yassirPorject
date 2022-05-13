package com.example.mbarki_mohamed_yassir_project.domaine.local.dao.listMoveDao

import androidx.room.Dao
import androidx.room.Query
import com.example.mbarki_mohamed_yassir_project.domaine.local.dao.baseDAO.BaseDao
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao :BaseDao<Movie> {
    @Query("SELECT * FROM Movie")
    suspend fun getAllMovie():List<Movie>

    @Query("DELETE FROM Movie")
    suspend fun deleteAllMovie()
}