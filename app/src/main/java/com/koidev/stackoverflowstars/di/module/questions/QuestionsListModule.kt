package com.koidev.stackoverflowstars.di.module.questions

import com.koidev.domain.Question
import com.koidev.domain.interactor.GetQuestionsList
import com.koidev.domain.repository.StackOverFlowRepository
import com.koidev.stackoverflowstars.di.scope.QuestionsScope
import com.koidev.stackoverflowstars.global.UiThread
import com.koidev.stackoverflowstars.mvvm.vmodel.QuestionListViewModelFactory
import com.koidev.stackoverflowstars.utils.Paginator
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
