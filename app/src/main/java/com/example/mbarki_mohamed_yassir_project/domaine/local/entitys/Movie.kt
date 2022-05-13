package com.example.mbarki_mohamed_yassir_project.domaine.local.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "poster_path")
    val poster_path: String?,
    @ColumnInfo(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "genre_ids")
    val genre_ids: List<Int>,
    @ColumnInfo(name = "original_title")
    val original_title :String,
    @ColumnInfo(name = "original_language")
    val original_language:String,
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path:String?,
    @ColumnInfo(name = "popularity")
    val popularity:Double,
    @ColumnInfo(name = "vote_count")
    val vote_count:Int,
    @ColumnInfo(name = "video")
    val video:Boolean,
    @ColumnInfo(name = "vote_average")
    val vote_average:Double
)

