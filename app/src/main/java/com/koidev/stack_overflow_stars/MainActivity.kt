package com.koidev.stack_overflow_stars

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.koidev.domain.common.disposedBy
import com.koidev.stack_overflow_stars.mvvm.vmodel.QuestionListViewModel
import com.koidev.stack_overflow_stars.mvvm.vmodel.QuestionListViewModelFactory
import com.koidev.stack_overflow_stars.utils.componentManager
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val subscriptions by lazy { CompositeDisposable() }

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


        questionsListViewModel.observeQuestionsList().subscribe { list ->
            Toast.makeText(this, list[0].toString(), Toast.LENGTH_LONG).show()
        }.disposedBy(subscriptions)
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