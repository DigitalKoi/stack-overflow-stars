package com.koidev.remote.service

import com.google.gson.GsonBuilder
import com.koidev.remote.service.api.StackOverFlowApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteServiceFactory(
    client: OkHttpClient
) {

    fun buildStackOverFlowApi(baseUrl: String) = builder
        .baseUrl(baseUrl)
        .build()
        .create(StackOverFlowApi::class.java)

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val builder = Retrofit.Builder()
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
}