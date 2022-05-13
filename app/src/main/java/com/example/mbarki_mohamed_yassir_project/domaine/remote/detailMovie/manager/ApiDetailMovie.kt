package com.example.mbarki_mohamed_yassir_project.domaine.remote.detailMovie.manager

import com.example.mbarki_mohamed_yassir_project.domaine.remote.detailMovie.models.RemoteDetailMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDetailMovie {
    @GET("movies/")
    suspend fun getDetailMovieList(
        @Query("api_key") apiKey: String,
        @Path("movie_id") movie_id: Int
    ): Response<RemoteDetailMovieResponse>
}