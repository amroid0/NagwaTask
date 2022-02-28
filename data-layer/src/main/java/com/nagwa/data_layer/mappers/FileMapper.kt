package com.nagwa.data_layer.mappers

import com.nagwa.data_layer.models.FileResponseModel
import com.nagwa.domain_layer.entities.FileEntity
import javax.inject.Inject

open class FileMapper @Inject constructor(): Mapper<FileEntity, FileResponseModel> {


    override fun mapFromEntity(type: FileEntity): FileResponseModel {
        return FileResponseModel(type.id,type.type,type.name,type.url)
    }


    override fun mapToEntity(type: FileResponseModel): FileEntity {
        return FileEntity(type.id,type.type,type.name,type.url)
    }


}