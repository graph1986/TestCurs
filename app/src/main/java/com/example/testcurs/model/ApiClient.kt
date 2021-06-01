package com.example.testcurs.model

import com.example.testcurs.model.entities.ValCurs
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface ApiClient {

    @GET("scripts/XML_dynamic.asp")
    fun fetchRecords(
        @Query("date_req1") date1: String,
        @Query("date_req2") date2: String,
        @Query("VAL_NM_RQ") id: String
    ): Single<ValCurs>

    class Factory @Inject constructor() {
        fun create() = Retrofit.Builder().run {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            baseUrl(BASE_URL)
            addConverterFactory(SimpleXmlConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            build().create(ApiClient::class.java)
        }
    }

    private companion object {
        private const val BASE_URL = "https://www.cbr.ru/"
    }

}