package com.koidev.stackoverflowstars.di.component

import com.koidev.stackoverflowstars.di.component.questions.QuestionsListSubComponent
import com.koidev.stackoverflowstars.di.module.AppModule
import com.koidev.stackoverflowstars.di.module.DataModule
import com.koidev.stackoverflowstars.di.module.NavigationModule
import com.koidev.stackoverflowstars.di.module.NetworkModule
import com.koidev.stackoverflowstars.di.module.questions.QuestionsListModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (NetworkModule::class),
    (DataModule::class),
    (NavigationModule::class)
])
interface MainComponent {

    fun plus(questionsListModule: QuestionsListModule): QuestionsListSubComponent
}