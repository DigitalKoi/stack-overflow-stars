package com.koidev.stackoverflowstars.navigation

import androidx.fragment.app.Fragment
import com.koidev.stackoverflowstars.ui.questions.list.QuestionsListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    class QuestionsListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? = QuestionsListFragment.newInstance()
    }
}