package com.example.testcurs.core

import android.app.Activity
import android.app.Application
import com.example.testcurs.di.components.AppComponent
import com.example.testcurs.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CurrencyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .getContext(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = injector

}