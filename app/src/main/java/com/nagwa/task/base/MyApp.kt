package com.nagwa.task.base

import android.app.Application
import com.nagwa.task.di.component.AppComponent
import com.nagwa.task.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApp:Application() , HasAndroidInjector {

    var appComponent: AppComponent? = null

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent?.inject(this)
    }
}