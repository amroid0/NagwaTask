package com.nagwa.domain_layer.entities

data class FileEntity(
    val id: String,
    val type: String,
    val name: String,
    val url: String,
    var state: FileState=FileState.Idle
)
