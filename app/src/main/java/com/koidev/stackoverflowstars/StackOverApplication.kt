package com.koidev.stackoverflowstars

import android.app.Application
import com.koidev.stackoverflowstars.di.common.ComponentManager
import com.koidev.stackoverflowstars.di.common.DefaultComponentManager
import com.facebook.stetho.Stetho
import com.koidev.stackoverflowstars.di.common.ComponentManager
import com.koidev.stackoverflowstars.di.common.DefaultComponentManager
import timber.log.Timber


class StackOverApplication : Application() {

    private val componentManager by lazy {
        DefaultComponentManager(
            applicationContext
        )
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initStatho()
    }

    fun getComponentManager(): ComponentManager = componentManager

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } // else for crashlytics
    }

    private fun initStatho() {
        Stetho.initializeWithDefaults(this)
    }
}