package com.iml.myapplication.webclient

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WebClient {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://private-222d3-homework5.apiary-mock.com/"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(buildHttpClient())
            .build()
    }


    private fun buildGson(): Gson {
        return GsonBuilder().serializeNulls()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()
    }

    private fun buildHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(1, TimeUnit.MINUTES)
        okHttpClientBuilder.writeTimeout(1, TimeUnit.MINUTES)
        okHttpClientBuilder.readTimeout(1, TimeUnit.MINUTES)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(interceptor)

        okHttpClientBuilder.addInterceptor { chain: Interceptor.Chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("IMSI", "357175048449937")
                .addHeader("IMEI", "510110406068589")
                .build()
            return@addInterceptor chain.proceed(request)
        }
        return okHttpClientBuilder.build()
    }

    fun getRetrofit(): Retrofit {
        return retrofit!!
    }
}