package com.example.testcurs.di

import com.example.testcurs.model.ApiClient
import com.example.testcurs.model.HttpCurrencyRepo
import com.example.testcurs.model.repositories.CurrencyRepo
import com.example.testcurs.ui.MainActivity
import com.example.testcurs.ui.MainContract
import com.example.testcurs.ui.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@Module(includes = [AndroidSupportInjectionModule::class])
abstract class AppModule {

    @Binds
    abstract fun repo(repo: HttpCurrencyRepo): CurrencyRepo

    @Binds
    abstract fun presenter(presenter: MainPresenter): MainContract.Presenter

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun valuteApiClient(factory: ApiClient.Factory) = factory.create()
    }

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}