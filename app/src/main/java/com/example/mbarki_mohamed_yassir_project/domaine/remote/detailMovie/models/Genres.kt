package com.example.mbarki_mohamed_yassir_project.domaine.remote.detailMovie.models

import com.google.gson.annotations.SerializedName
data class Genres (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String
)