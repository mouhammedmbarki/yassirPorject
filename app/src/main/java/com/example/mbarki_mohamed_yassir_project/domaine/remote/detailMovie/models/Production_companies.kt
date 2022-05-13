package com.example.mbarki_mohamed_yassir_project.domaine.remote.detailMovie.models

import com.google.gson.annotations.SerializedName
data class Production_companies (
	@SerializedName("id") val id : Int,
	@SerializedName("logo_path") val logo_path : String,
	@SerializedName("name") val name : String,
	@SerializedName("origin_country") val origin_country : String
)