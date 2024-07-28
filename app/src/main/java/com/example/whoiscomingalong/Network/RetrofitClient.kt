package com.example.whoiscomingalong.Network

import com.google.gson.GsonBuilder
import okhttp3.Credentials
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authInterceptor = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", Credentials.basic("user", "password"))
        val request = requestBuilder.build()
        chain.proceed(request)
    }

    private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(logging)
        addInterceptor(authInterceptor)
    }.build()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}