package com.example.mbarki_mohamed_yassir_project.domaine.remote.detailMovie.manager

import com.example.mbarki_mohamed_yassir_project.domaine.remote.detailMovie.models.RemoteDetailMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDetailMovie {
    @GET("movies/{movie_id}")
    suspend fun getDetailMovieList(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String
    ): Response<RemoteDetailMovieResponse>
}