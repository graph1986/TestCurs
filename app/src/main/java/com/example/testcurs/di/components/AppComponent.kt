package com.example.testcurs.di.components

import android.content.Context
import com.example.testcurs.core.CurrencyApplication
import com.example.testcurs.di.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent{

    fun inject(app:CurrencyApplication)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun getContext(context:Context):Builder

        fun build():AppComponent
    }

}