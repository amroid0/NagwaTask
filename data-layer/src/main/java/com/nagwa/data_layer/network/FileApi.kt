package com.nagwa.data_layer.network

import com.nagwa.data_layer.models.FileResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface FileApi {
    @Headers("mock:true")
    @GET("/movies")
    fun getFiles(): Single<List<FileResponseModel>>
}