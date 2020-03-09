package com.koidev.stackoverflowstars.di.module

import com.koidev.data.repository.questions.DefaultStackOverFlowRepository
import com.koidev.data.repository.questions.StackOverFlowRemote
import com.koidev.data.store.stackoverflow.StackOverFlowCacheDataStore
import com.koidev.data.store.stackoverflow.StackOverFlowFactory
import com.koidev.data.store.stackoverflow.StackOverFlowRemoteDataStore
import com.koidev.domain.repository.StackOverFlowRepository
import com.koidev.remote.implementations.DefaultStackOverFlowRemote
import com.koidev.remote.service.api.StackOverFlowApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton @Provides
    fun provideStackOverFlowRepository(factory: StackOverFlowFactory): StackOverFlowRepository =
        DefaultStackOverFlowRepository(factory)

    @Singleton @Provides
    fun provideStackOverFlowDataStoreFactory(
        cache: StackOverFlowCacheDataStore,
        remote: StackOverFlowRemoteDataStore
    ) = StackOverFlowFactory(cache, remote)

    @Singleton @Provides
    fun provideStackOverFlowCacheStoreFactory() = StackOverFlowCacheDataStore()

    @Singleton @Provides
    fun provideStackOverFlowRemoteStoreFactory(remote: StackOverFlowRemote) =
        StackOverFlowRemoteDataStore(remote)

    @Singleton @Provides
    fun provideStackOverFlowRemote(api: StackOverFlowApi): StackOverFlowRemote =
        DefaultStackOverFlowRemote(api)

}