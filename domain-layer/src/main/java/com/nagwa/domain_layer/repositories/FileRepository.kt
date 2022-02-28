package com.nagwa.domain_layer.repositories

import com.nagwa.domain_layer.entities.FileEntity
import io.reactivex.Observable
import io.reactivex.Single

interface FileRepository {
    fun getFiles(): Single<List<FileEntity>>

    fun downloadFile(entity: FileEntity): Observable<Int>
}