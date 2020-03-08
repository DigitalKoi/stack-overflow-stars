package com.koidev.stackoverflowstars.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(context: Context) {

    private val appContext = context.applicationContext

    @Singleton @Provides
    fun provideAppContext() = appContext
}