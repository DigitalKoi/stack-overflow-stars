package com.koidev.stack_overflow_stars.mvvm.vmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.koidev.domain.interactor.GetQuestionsList

class QuestionListViewModelFactory(
    private val getQuestionsList: GetQuestionsList
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        QuestionListViewModel(
            getQuestionsList = getQuestionsList
        ) as T
}