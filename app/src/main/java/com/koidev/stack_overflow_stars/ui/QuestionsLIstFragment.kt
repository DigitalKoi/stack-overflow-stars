package com.koidev.stack_overflow_stars.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.koidev.stack_overflow_stars.R
import com.koidev.stack_overflow_stars.mvvm.vmodel.QuestionListViewModel
import com.koidev.stack_overflow_stars.mvvm.vmodel.QuestionListViewModelFactory
import com.koidev.stack_overflow_stars.utils.componentManager
import com.koidev.stack_overflow_stars.utils.hideKeyboard
import javax.inject.Inject

class QuestionsLIstFragment : BaseFragment() {

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(QuestionListViewModel::class.java)
    }

    @Inject
    lateinit var factory: QuestionListViewModelFactory

    companion object {
        fun newInstance() = QuestionsLIstFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_questions_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: add view listeners here
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //TODO: viewModel subscriptions here
    }

    override fun onDestroy() {
        hideKeyboard(activity?.currentFocus)
        super.onDestroy()
        subscriptions.clear()
    }

    override fun injectDependencies() {
        componentManager().createQuestionsListSubComponent().inject(this)
    }

    override fun releaseDependencies() {
        componentManager().releaseQuestionsListSubComponent()
    }

    override fun onBackPressed() {
        //TODO: add backPress logic here
    }
}