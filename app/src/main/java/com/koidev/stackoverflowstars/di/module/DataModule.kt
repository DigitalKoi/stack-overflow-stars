package com.koidev.stackoverflowstars.di.module

import android.content.Context
import com.koidev.data.repository.questions.DefaultStackOverFlowRepository
import com.koidev.data.repository.questions.StackOverFlowCache
import com.koidev.data.repository.questions.StackOverFlowFactory
import com.koidev.data.repository.questions.StackOverFlowRemote
import com.koidev.domain.repository.StackOverFlowRepository
import com.koidev.persistence.StackOverFlowDatabase
import com.koidev.persistence.implementation.DefaultStackOverFlowCache
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
        cache: StackOverFlowCache,
        remote: StackOverFlowRemote
    ) = StackOverFlowFactory(cache, remote)

    @Singleton @Provides
    fun provideStackOverFlowRemote(api: StackOverFlowApi): StackOverFlowRemote =
        DefaultStackOverFlowRemote(api)

    @Singleton @Provides
    fun provideStackOverFlowCache(db: StackOverFlowDatabase): StackOverFlowCache =
        DefaultStackOverFlowCache(db)

    @Singleton @Provides
    fun provideDataBase(context: Context) = StackOverFlowDatabase.getInstance(context)
}