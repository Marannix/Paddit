package com.example.paddit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface ApiService {

//    private fun provideRetrofit(): Retrofit {
//        // Debugging purpose to show request and response information
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//
//        val httpClient = OkHttpClient.Builder()
//            .readTimeout(1, TimeUnit.MINUTES)
//            .writeTimeout(1, TimeUnit.MINUTES)
//            .connectTimeout(1, TimeUnit.MINUTES)
//            .addInterceptor(logging)
//            .build()
//
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(httpClient)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    private fun providePostApi(retrofit: Retrofit): PostApi {
//        return retrofit.create(PostApi::class.java)
//    }
//
//    fun postApi(): PostApi {
//        return providePostApi(provideRetrofit())
//    }
}