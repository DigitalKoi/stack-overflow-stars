package com.koidev.stackoverflowstars.ui.questions.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.koidev.domain.Question
import com.koidev.domain.common.disposedBy
import com.koidev.stackoverflowstars.R
import com.koidev.stackoverflowstars.mvvm.vmodel.QuestionListViewModel
import com.koidev.stackoverflowstars.mvvm.vmodel.QuestionListViewModelFactory
import com.koidev.stackoverflowstars.ui.BaseFragment
import com.koidev.stackoverflowstars.ui.list.PaginalAdapter
import com.koidev.stackoverflowstars.ui.list.QuestionsAdapterDelegate
import com.koidev.stackoverflowstars.ui.list.isSame
import com.koidev.stackoverflowstars.utils.componentManager
import com.koidev.stackoverflowstars.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_questions_list.*
import javax.inject.Inject

class QuestionsListFragment : BaseFragment() {

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(QuestionListViewModel::class.java)
    }

    @Inject
    lateinit var factory: QuestionListViewModelFactory

    companion object {
        fun newInstance() =
            QuestionsListFragment()
    }

    private val adapter by lazy {
        PaginalAdapter(
            { viewModel.loadNextEventsPage() },
            { o, n ->
                if (o is Question && n is Question) {
                    o.isSame(n)
                } else false
            },
            QuestionsAdapterDelegate { viewModel.onItemClick(it) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_questions_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        paginalRenderView.init(
            { viewModel.refreshEvents() },
            adapter
        )
        viewModel.observeRenderState()
            .subscribe(paginalRenderView::render)
            .disposedBy(subscriptions)

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
        viewModel.onBackPressed()
    }
}