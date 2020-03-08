package com.koidev.stack_overflow_stars.navigation

import androidx.fragment.app.Fragment
import com.koidev.stack_overflow_stars.ui.questions.list.QuestionsListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    class QuestionsListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? = QuestionsListFragment.newInstance()
    }
}