package com.nagwa.data_layer.network.datasources

import com.nagwa.data_layer.models.FileResponseModel
import io.reactivex.Observable
import io.reactivex.Single
import java.io.File

interface BaseFileDataSource {
    fun getFiles(): Single<List<FileResponseModel>>
    fun downloadFile(uri: String): Observable<Int>

}