package com.koidev.stackoverflowstars.mvvm.vmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.koidev.domain.Question
import com.koidev.domain.interactor.GetQuestionsList
import com.koidev.stackoverflowstars.utils.Paginator
import ru.terrakok.cicerone.Router

class QuestionListViewModelFactory(
    private val router: Router,
    private val getQuestionsList: GetQuestionsList,
    private val paginator: Paginator.Store<Question>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        QuestionListViewModel(
            router = router,
            getQuestionsList = getQuestionsList,
            paginator = paginator
        ) as T
}