package com.example.mbarki_mohamed_yassir_project.domaine.local.DBManager

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mbarki_mohamed_yassir_project.domaine.local.TypeConverts.DataBaseTypeConverter
import com.example.mbarki_mohamed_yassir_project.domaine.local.dao.DetailMoveDao.DetailMovieDao
import com.example.mbarki_mohamed_yassir_project.domaine.local.dao.listMoveDao.MovieDao
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.DetailMovie
import com.example.mbarki_mohamed_yassir_project.domaine.local.entitys.Movie
@TypeConverters(DataBaseTypeConverter::class)
@Database(
    entities = [Movie::class, DetailMovie::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
   abstract fun movieDao(): MovieDao
   abstract fun DetailMovieDao(): DetailMovieDao
    companion object {
        val DATABASE_NAME: String = "MovieDatabase"
    }
}