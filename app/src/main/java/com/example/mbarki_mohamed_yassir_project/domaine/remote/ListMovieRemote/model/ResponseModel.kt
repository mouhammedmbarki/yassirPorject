package com.example.mbarki_mohamed_yassir_project.domaine.remote.ListMovieRemote.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val page: Int,
    val results: List<RemoteMovieModel>,
    val total_results: Int,
    val total_pages: Int
)