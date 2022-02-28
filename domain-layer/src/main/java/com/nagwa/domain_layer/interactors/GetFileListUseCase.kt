package com.nagwa.domain_layer.interactors

import com.nagwa.domain_layer.repositories.FileRepository
import javax.inject.Inject

class GetFileListUseCase @Inject constructor(private val repo: FileRepository) {
     fun execute() =repo.getFiles()
}