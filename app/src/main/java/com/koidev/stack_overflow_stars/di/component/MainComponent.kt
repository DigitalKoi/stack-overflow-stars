package com.koidev.stack_overflow_stars.di.component

import com.koidev.stack_overflow_stars.di.component.questions.QuestionsListSubComponent
import com.koidev.stack_overflow_stars.di.module.AppModule
import com.koidev.stack_overflow_stars.di.module.DataModule
import com.koidev.stack_overflow_stars.di.module.NavigationModule
import com.koidev.stack_overflow_stars.di.module.NetworkModule
import com.koidev.stack_overflow_stars.di.module.questions.QuestionsListModule
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