package com.nagwa.data_layer.network.datasources

import com.nagwa.data_layer.network.FileApi
import com.nagwa.data_layer.network.FileDownloader
import javax.inject.Inject

class FileDataSourceImp @Inject constructor(
    val fileApi: FileApi,
    val fileDownloader: FileDownloader
) : BaseFileDataSource {
    override fun getFiles() = fileApi.getFiles()
    override fun downloadFile(uri: String) = fileDownloader.download(uri)


}