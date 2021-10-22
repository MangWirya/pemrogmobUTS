package com.example.pemrogmobuts.model

import com.google.gson.annotations.SerializedName

data class ResponseAddMatkul (
    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("status")
    val status: String? = null
)