package com.koidev.stackoverflowstars.di.module

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.koidev.remote.service.RemoteServiceFactory
import com.koidev.remote.service.api.StackOverFlowApi
import com.koidev.remote.service.httjp.OkHttpFactory
import com.koidev.remote.service.interceptor.ApiRequestInterceptor
import com.koidev.remote.service.interceptor.ApiResponseInterceptor
import dagger.Module
import dagger.Provides
import fr.speekha.httpmocker.MockResponseInterceptor
import fr.speekha.httpmocker.gson.GsonMapper
import fr.speekha.httpmocker.policies.SingleFilePolicy
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
        apiResponseInterceptor: ApiResponseInterceptor,
        mockResponseInterceptor: MockResponseInterceptor,
        stethoInterceptor: StethoInterceptor
    ): OkHttpClient {

        val interceptors = listOf(
            apiRequestInterceptor,
            apiResponseInterceptor,
//            mockResponseInterceptor,
            stethoInterceptor
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

    @Singleton @Provides
    fun provideStetho() = StethoInterceptor()

    @Singleton @Provides
    fun provideMockResponseInterceptor(context: Context) = MockResponseInterceptor
        .Builder()
        .decodeScenarioPathWith(SingleFilePolicy("orgs/kotlin/repos.json"))
        .loadFileWith(context.assets::open)
        .setInterceptorStatus(MockResponseInterceptor.Mode.DISABLED)
        .parseScenariosWith(GsonMapper())
        .addFakeNetworkDelay(500)
        .build()
}