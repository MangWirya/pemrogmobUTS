package com.example.pemrogmobuts.model

import com.google.gson.annotations.SerializedName

data class ResponseMatkul(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItem(

	@field:SerializedName("kode")
	val kode: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("hari")
	val hari: String? = null,

	@field:SerializedName("sesi")
	val sesi: String? = null,

	@field:SerializedName("nim_progmob")
	val nimProgmob: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("sks")
	val sks: String? = null,

)
