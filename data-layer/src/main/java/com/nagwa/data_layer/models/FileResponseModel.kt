package com.nagwa.data_layer.models

import com.squareup.moshi.Json

data class FileResponseModel(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
)
