package com.koidev.stack_overflow_stars.mvvm.vmodel

import androidx.lifecycle.ViewModel
import com.koidev.domain.interactor.GetQuestionsList
import io.reactivex.disposables.CompositeDisposable

class QuestionListViewModel(
    val getQuestionsList: GetQuestionsList
) : ViewModel() {

    private val subscribtion = CompositeDisposable()


}