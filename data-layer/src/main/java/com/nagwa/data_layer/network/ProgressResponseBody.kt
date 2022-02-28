package com.nagwa.data_layer.network;

import okhttp3.ResponseBody;
import okio.*

public class ProgressResponseBody(
        private val responseBody:ResponseBody,
        private val onProgress: (contentLength:Long,progress: Long) -> Unit
    ) : ResponseBody() {

        private var bufferedSource: BufferedSource? = null

        override fun contentType() = responseBody.contentType()

        override fun contentLength() = responseBody.contentLength()

        override fun source() = bufferedSource ?: source(responseBody.source()).buffer().also { bufferedSource = it }

        private fun source(source: Source): Source {
            return object : ForwardingSource(source) {
                var totalBytesRead = 0L

                override fun read(sink: Buffer, byteCount: Long): Long {
                    return super.read(sink, byteCount).apply {
                        val bytesRead = this
                        totalBytesRead += if (bytesRead != -1L) bytesRead else 0

                        if (bytesRead != -1L) {
                            val progress = 100 * totalBytesRead / responseBody.contentLength()
                            onProgress(responseBody.contentLength(),progress)
                        }else{
                            onProgress(responseBody.contentLength(),bytesRead)
                        }
                    }
                }
            }
        }
    }

