package com.nagwa.task.di.modules

import android.content.Context
import com.nagwa.data_layer.network.FileApi
import com.nagwa.data_layer.network.MockRequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [AppModule::class])
class NetworkModule {

    val url = "https://nagwa.free.beeceptor.com"



    @Provides
    @Singleton
    fun provideOkHttp(context:Context): OkHttpClient {
        return OkHttpClient().newBuilder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(MockRequestInterceptor(context))
               .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitApi(retrofit: Retrofit): FileApi {
        return retrofit.create(FileApi::class.java)
    }

}