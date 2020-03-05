package com.koidev.stack_overflow_stars.di.component

import com.koidev.stack_overflow_stars.di.module.AppModule
import com.koidev.stack_overflow_stars.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (NetworkModule::class)
])
interface MainComponent {
}