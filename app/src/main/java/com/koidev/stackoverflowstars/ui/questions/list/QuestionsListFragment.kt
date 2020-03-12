package com.koidev.stackoverflowstars.ui.questions.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import com.koidev.domain.Question
import com.koidev.domain.common.disposedBy
import com.koidev.stackoverflowstars.R
import com.koidev.stackoverflowstars.mvvm.vmodel.QuestionListViewModel
import com.koidev.stackoverflowstars.mvvm.vmodel.QuestionListViewModelFactory
import com.koidev.stackoverflowstars.ui.BaseFragment
import com.koidev.stackoverflowstars.ui.list.HistoryQuestionAdapter
import com.koidev.stackoverflowstars.ui.list.PaginalAdapter
import com.koidev.stackoverflowstars.ui.list.QuestionsAdapterDelegate
import com.koidev.stackoverflowstars.ui.list.isSame
import com.koidev.stackoverflowstars.utils.componentManager
import com.koidev.stackoverflowstars.utils.filterRapidClicks
import com.koidev.stackoverflowstars.utils.hideKeyboard
import com.koidev.stackoverflowstars.utils.visible
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

    private val paginalAdapter by lazy {
        PaginalAdapter(
            { viewModel.loadNextEventsPage() },
            { o, n ->
                if (o is Question && n is Question) {
                    o.isSame(n)
                } else false
            },
            QuestionsAdapterDelegate(
                clickListenerItem = viewModel::onItemClick,
                clickListenerProfile = viewModel::onItemClick,
                subscriptions = subscriptions
            ))
    }

    private val historyQuestionAdapter by lazy {
        HistoryQuestionAdapter(
            data = mutableListOf(),
            onClickItem = viewModel::onItemClick,
            onClickImage = viewModel::onItemClick,
            subscriptions = subscriptions
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_questions_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paginalRenderView.init(
            { viewModel.refreshEvents() },
            paginalAdapter
        )

        searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyQuestionAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.observeRenderState()
            .subscribe(paginalRenderView::render)
            .disposedBy(subscriptions)

        // TODO: move to new fragment
        viewModel.observeHistoryList()
            .subscribe(historyQuestionAdapter::replace)
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchView = menu.findItem(R.id.action_search_questions_search).actionView as SearchView

        searchView.queryTextChanges()
            .filterRapidClicks()
            .map(CharSequence::toString)
            .subscribe { query ->
                viewModel.getQuestionsListFromDataBase(query ?: "")
                showPaginalQuestionsList(query.isNullOrEmpty())
            }.disposedBy(subscriptions)


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getQuestionsListFromDataBase(newText ?: "")
                showPaginalQuestionsList(newText.isNullOrEmpty())
                return true
            }
        })

        super.onPrepareOptionsMenu(menu)
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
    }

    private fun showPaginalQuestionsList(isShown: Boolean) {
        paginalRenderView.visible(isShown)
        searchRecyclerView.visible(!isShown)
    }
}