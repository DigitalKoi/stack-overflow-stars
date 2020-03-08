package com.koidev.stackoverflowstars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.koidev.stackoverflowstars.mvvm.vmodel.QuestionListViewModel
import com.koidev.stackoverflowstars.mvvm.vmodel.QuestionListViewModelFactory
import com.koidev.stackoverflowstars.utils.componentManager
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val questionsListViewModel by lazy {
        ViewModelProvider(this, factory).get(QuestionListViewModel::class.java)
    }

    @Inject
    lateinit var factory: QuestionListViewModelFactory

    private val ciceroneNavigator by lazy {
        SupportAppNavigator(this, R.id.fragmentContainer)
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()

        //TODO: change logic
        questionsListViewModel.routeToQuestionsList()
    }

    override fun onPause() {
        Timber.d("onPause")
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onPause")
        navigatorHolder.setNavigator(ciceroneNavigator)
    }

    override fun onDestroy() {
        Timber.d("onDestroy")
        super.onDestroy()
    }
    private fun injectDependencies() {
        componentManager().createQuestionsListSubComponent().inject(this)
    }

}