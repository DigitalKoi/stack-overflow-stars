package com.koidev.stack_overflow_stars

import android.app.Application
import com.koidev.stack_overflow_stars.di.common.ComponentManager
import com.koidev.stack_overflow_stars.di.common.DefaultComponentManager
import timber.log.Timber


class StackOverApplication : Application() {

    private val componentManager by lazy {
        DefaultComponentManager(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    fun getComponentManager(): ComponentManager = componentManager

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } // else for crashlytics
    }
}