package com.koidev.stackoverflowstars.navigation

import androidx.fragment.app.Fragment
import com.koidev.stack_overflow_stars.ui.QuestionsLIstFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    class QuestionsListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? = QuestionsLIstFragment.newInstance()
    }
}