package com.nagwa.domain_layer.interactors

import com.nagwa.domain_layer.entities.FileEntity
import com.nagwa.domain_layer.repositories.FileRepository
import javax.inject.Inject

class DownloadFileUseCase @Inject constructor(private val repo: FileRepository){
     fun execute(entity: FileEntity) =repo.downloadFile(entity)
}