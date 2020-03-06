package com.koidev.stack_overflow_stars.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment(
        private val isFragmentScope : Boolean = true
) : Fragment() {

    val subscriptions by lazy { CompositeDisposable() }

    abstract fun injectDependencies()
    abstract fun releaseDependencies()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFragmentScope)
            releaseDependencies()
    }


    abstract fun onBackPressed()
}
