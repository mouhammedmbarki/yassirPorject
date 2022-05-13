package com.example.mbarki_mohamed_yassir_project.domaine.local.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetailMovie(
    @PrimaryKey
    @ColumnInfo
    val id:Int,
    @ColumnInfo(name ="movieUrl" )
    val movieUrl: String,
    @ColumnInfo(name = "Title")
    val Title: String,
    @ColumnInfo(name = "dateRelease")
    val dateRelease: String,
    @ColumnInfo(name = "overview")
    val overview: String
)