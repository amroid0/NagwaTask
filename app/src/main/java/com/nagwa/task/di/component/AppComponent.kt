package com.nagwa.task.di.component

import android.app.Application
import com.nagwa.task.base.MyApp
import com.nagwa.task.di.modules.ActivityBuilder
import com.nagwa.task.di.modules.AppModule
import com.nagwa.task.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,NetworkModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent {

    fun inject(app: MyApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}