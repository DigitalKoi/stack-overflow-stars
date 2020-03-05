package com.koidev.stack_overflow_stars.di.common

import android.content.Context
import com.koidev.stack_overflow_stars.BuildConfig
import com.koidev.stack_overflow_stars.di.component.DaggerMainComponent
import com.koidev.stack_overflow_stars.di.module.AppModule
import com.koidev.stack_overflow_stars.di.module.DataModule
import com.koidev.stack_overflow_stars.di.module.NetworkModule

class DefaultComponentManager constructor(context: Context) : ComponentManager {

    private val mainComponent = DaggerMainComponent.builder()
        .appModule(AppModule(context))
        .networkModule(NetworkModule(BuildConfig.DIRECTIONS_URL))
        .dataModule(DataModule())
        .build()
}