package com.koidev.stack_overflow_stars.di.module.questions

import com.koidev.domain.Question
import com.koidev.domain.interactor.GetQuestionsList
import com.koidev.domain.repository.StackOverFlowRepository
import com.koidev.stack_overflow_stars.di.scope.QuestionsScope
import com.koidev.stack_overflow_stars.global.UiThread
import com.koidev.stack_overflow_stars.mvvm.vmodel.QuestionListViewModelFactory
import com.koidev.stack_overflow_stars.utils.Paginator
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class QuestionsListModule {

    @Provides @QuestionsScope
    fun provideQuestionListViewModelFactory(
        router: Router,
        getQuestionsList: GetQuestionsList,
        paginator: Paginator.Store<Question>
    ) = QuestionListViewModelFactory(
        router = router,
        getQuestionsList = getQuestionsList,
        paginator = paginator
    )

    @Provides @QuestionsScope
    fun provideGetQuestionsList(
        stackOverFlowRepository: StackOverFlowRepository
    ) = GetQuestionsList(
        postExecutionThread = UiThread(),
        stackOverFlowRepository = stackOverFlowRepository
    )

    @Provides @QuestionsScope
    fun providePaginator() = Paginator.Store<Question>(UiThread())
}
