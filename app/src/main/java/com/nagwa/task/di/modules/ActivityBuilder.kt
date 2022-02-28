package com.nagwa.task.di.modules

import com.nagwa.task.file_download.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FilesModule::class])
    abstract fun bindMainActivity(): MainActivity
}