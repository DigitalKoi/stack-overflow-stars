package com.koidev.stack_overflow_stars.di.module.questions

import com.koidev.domain.interactor.GetQuestionsList
import com.koidev.domain.repository.StackOverFlowRepository
import com.koidev.stack_overflow_stars.di.scope.QuestionsScope
import com.koidev.stack_overflow_stars.global.UiThread
import com.koidev.stack_overflow_stars.mvvm.vmodel.QuestionListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class QuestionsListModule {

    @Provides @QuestionsScope
    fun provideQuestionListViewModelFactory(
        getQuestionsList: GetQuestionsList
    ) = QuestionListViewModelFactory(
        getQuestionsList = getQuestionsList
    )

    @Provides @QuestionsScope
    fun provideGetQuestionsList(
        stackOverFlowRepository: StackOverFlowRepository
    ) = GetQuestionsList(
        postExecutionThread = UiThread(),
        stackOverFlowRepository = stackOverFlowRepository
    )
}