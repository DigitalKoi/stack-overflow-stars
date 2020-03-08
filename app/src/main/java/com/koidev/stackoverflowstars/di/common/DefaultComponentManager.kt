package com.koidev.stackoverflowstars.di.common

import android.content.Context
import com.koidev.stackoverflowstars.BuildConfig
import com.koidev.stackoverflowstars.di.component.DaggerMainComponent
import com.koidev.stackoverflowstars.di.component.questions.QuestionsListSubComponent
import com.koidev.stackoverflowstars.di.module.AppModule
import com.koidev.stackoverflowstars.di.module.DataModule
import com.koidev.stackoverflowstars.di.module.NavigationModule
import com.koidev.stackoverflowstars.di.module.NetworkModule
import com.koidev.stackoverflowstars.di.module.questions.QuestionsListModule

class DefaultComponentManager constructor(context: Context) : ComponentManager {

    private val mainComponent = DaggerMainComponent.builder()
        .appModule(AppModule(context))
        .networkModule(NetworkModule(BuildConfig.DIRECTIONS_URL))
        .dataModule(DataModule())
        .navigationModule(NavigationModule())
        .build()

    private var questionsListSubComponent: QuestionsListSubComponent? = null


    override fun createQuestionsListSubComponent(): QuestionsListSubComponent =
        mainComponent.plus(QuestionsListModule()).also {
            questionsListSubComponent = it
        }

    override fun releaseQuestionsListSubComponent() {
        questionsListSubComponent = null
    }
}