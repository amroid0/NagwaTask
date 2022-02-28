package com.nagwa.domain_layer.entities

sealed class FileState{
    object Idle : FileState()
    class DownLoading (val progress:Int) : FileState()
    object Completed : FileState()
    object Failure : FileState()

}