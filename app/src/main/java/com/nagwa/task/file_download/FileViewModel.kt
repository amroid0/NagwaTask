package com.nagwa.task.file_download

import androidx.lifecycle.MutableLiveData
import com.nagwa.task.base.BaseViewModel
import com.nagwa.task.utils.ResponseState
import com.nagwa.domain_layer.entities.FileEntity
import com.nagwa.domain_layer.entities.FileState
import com.nagwa.domain_layer.interactors.DownloadFileUseCase
import com.nagwa.domain_layer.interactors.GetFileListUseCase
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FileViewModel @Inject constructor(
    private val getFileListUseCase: GetFileListUseCase,
    private val downloadFileUseCase: DownloadFileUseCase
) : BaseViewModel() {
    val listState = MutableLiveData<ResponseState<List<FileEntity>>>()
    val fileDownloadState = MutableLiveData<FileEntity>()

    fun getFilesList() {
        listState.value=ResponseState.Loading
        addDisposable(
            getFileListUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .subscribe({
                  listState.value=ResponseState.Success(it)
                }, {
                    listState.value=ResponseState.Error(it)
                }
                ))
    }
    fun downloadFile(entity: FileEntity) {
        addDisposable(
            downloadFileUseCase.execute(entity)
                .throttleFirst(1, TimeUnit.SECONDS)
                .toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .subscribe({
                    entity.state = FileState.DownLoading(it)
                    fileDownloadState.value=entity
                }, {
                    entity.state = FileState.Failure
                    fileDownloadState.value=entity
                }, {
                    entity.state = FileState.Completed
                    fileDownloadState.value=entity

                }
    ))
}
}