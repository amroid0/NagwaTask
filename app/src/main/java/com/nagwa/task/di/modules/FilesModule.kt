package com.nagwa.task.di.modules

import com.nagwa.data_layer.network.datasources.BaseFileDataSource
import com.nagwa.data_layer.network.datasources.FileDataSourceImp
import com.nagwa.data_layer.repositories.FileRepositoryImp
import com.nagwa.domain_layer.repositories.FileRepository
import dagger.Module

@Module
interface FilesModule {

    @dagger.Binds
    fun bindDownloadFileRemoteDataSource(
        remoteDataSource:FileDataSourceImp
    ): BaseFileDataSource

    @dagger.Binds
    fun bindFileRepository(
        repo: FileRepositoryImp
    ): FileRepository

}