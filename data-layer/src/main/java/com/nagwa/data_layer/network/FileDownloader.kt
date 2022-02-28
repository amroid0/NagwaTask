package com.nagwa.data_layer.network

import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject

class FileDownloader @Inject constructor() {

    companion object {
        private const val COMPLETE = -1L
        private const val INFINITE = -1L
    }


    fun download(url: String): Observable<Int> {
        return Observable.create<Int> { emitter ->
            try {
                val request = Request.Builder()
                    .url(url)
                    .build()

                val client = OkHttpClient.Builder()
                    .addNetworkInterceptor { chain: Interceptor.Chain ->
                        val originalResponse = chain.proceed(chain.request())
                        originalResponse.newBuilder()
                            .body(originalResponse.body?.let { responseBody ->
                                ProgressResponseBody(responseBody) { contentLength, progress ->
                                    if (progress == COMPLETE) {
                                        emitter.onComplete()
                                    } else {
                                        if (contentLength == INFINITE) {
                                            emitter.onNext(INFINITE.toInt())
                                        } else {
                                            emitter.onNext(progress.toInt())
                                        }
                                    }
                                }
                            })
                            .build()
                    }
                    .build()
                val response = client.newCall(request).execute()
                if (!response.isSuccessful) throw IOException("code $response")

                response.body?.string()
            } catch (exception: Exception) {
                emitter.onError(exception)
            }
        }

    }
}