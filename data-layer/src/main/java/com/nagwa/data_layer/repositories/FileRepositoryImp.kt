package com.nagwa.data_layer.repositories

import com.nagwa.data_layer.mappers.FileMapper
import com.nagwa.data_layer.network.datasources.BaseFileDataSource
import com.nagwa.domain_layer.entities.FileEntity
import com.nagwa.domain_layer.repositories.FileRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import javax.inject.Inject

class FileRepositoryImp @Inject constructor(
    private val datasource: BaseFileDataSource,
    private val fileMapper: FileMapper
) : FileRepository {
    override fun getFiles(): Single<List<FileEntity>> {
        return datasource.getFiles().map {
            it.map { model ->
                fileMapper.mapToEntity(model)
            }
        }
    }

    override fun downloadFile(entity: FileEntity): Observable<Int> {
      return datasource.downloadFile(entity.url)
    }
}