package com.example.mbarki_mohamed_yassir_project.domaine.remote.ListMovieRemote.manager

import com.example.mbarki_mohamed_yassir_project.domaine.remote.ListMovieRemote.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMovieServices {
    @GET("discover/movie")
    suspend fun getMovieList(@Query("api_key") apiKey:String): Response<ResponseModel>
}