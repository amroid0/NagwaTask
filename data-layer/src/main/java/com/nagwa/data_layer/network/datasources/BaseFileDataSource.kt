package com.nagwa.data_layer.network.datasources

import com.nagwa.data_layer.models.FileResponseModel
import io.reactivex.Single

interface BaseFileDataSource {
    fun  getFiles(): Single<FileResponseModel>

}