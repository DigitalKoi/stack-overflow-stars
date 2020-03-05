package com.koidev.stack_overflow_stars.di.module

import com.koidev.remote.service.RemoteServiceFactory
import com.koidev.remote.service.api.StackOverFlowApi
import com.koidev.remote.service.httjp.OkHttpFactory
import com.koidev.remote.service.interceptor.ApiRequestInterceptor
import com.koidev.remote.service.interceptor.ApiResponseInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class NetworkModule(
    private val stackOverFlowUrl: String
) {

    @Singleton @Provides
    fun provideStackOverFlowApi(factory: RemoteServiceFactory): StackOverFlowApi =
        factory.buildStackOverFlowApi(stackOverFlowUrl)

    @Singleton @Provides
    fun provideHttpClient(
        apiRequestInterceptor: ApiRequestInterceptor,
        apiResponseInterceptor: ApiResponseInterceptor
    ): OkHttpClient {

        val interceptors = listOf(
            apiRequestInterceptor,
            apiResponseInterceptor
        )

        return OkHttpFactory().buildOkHttpClient(interceptors)
    }

    @Singleton @Provides
    fun provideRequestInterceptor() = ApiRequestInterceptor()

    @Singleton @Provides
    fun provideResponseInterceptor() = ApiResponseInterceptor()

    @Singleton @Provides
    fun provideRemoteServiceFactory(okHttpClient: OkHttpClient): RemoteServiceFactory =
        RemoteServiceFactory(okHttpClient)
}