package com.koidev.stackoverflowstars.ui.list

import android.os.Build
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.jakewharton.rxbinding3.view.clicks
import com.koidev.domain.Question
import com.koidev.domain.common.disposedBy
import com.koidev.stackoverflowstars.R
import com.koidev.stackoverflowstars.utils.filterRapidClicks
import com.koidev.stackoverflowstars.utils.humanTime
import com.koidev.stackoverflowstars.utils.inflate
import com.koidev.stackoverflowstars.utils.prettyCount
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_question.view.*

fun Question.isSame(other: Question) = questionId == other.questionId

class QuestionsAdapterDelegate(
    private val clickListenerItem: (Question) -> Unit,
    private val clickListenerProfile: (Question) -> Unit,
    private val subscriptions: CompositeDisposable
) : AdapterDelegate<MutableList<Any>>() {


    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean =
        items[position] is Question

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val root = parent.inflate(R.layout.item_question)

        return ViewHolder(
            root,
            clickListenerProfile,
            subscriptions
        )
    }

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        viewHolder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (viewHolder as ViewHolder).bind(items[position] as Question)


    private inner class ViewHolder(
        override val containerView: View,
        private val clickListenerProfile: (Question) -> Unit,
        private val subscriptions: CompositeDisposable
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private lateinit var item: Question

        init {
            containerView.setOnClickListener { clickListenerItem(item) }
        }

        fun bind(item: Question) {
            this.item = item
            containerView.apply {
                nameProfile.text = item.owner.displayName
                answerTitle.text = item.title.humanText()
                answersCount.apply {
                    text = item.answerCount.prettyCount()
                    setTextColor(item.isAnswered)
                }
                answersText.setTextColor(item.isAnswered)
                viewsCount.text = item.viewCount.prettyCount()
                votesCount.text = item.score.prettyCount()
                scoreProfile.text = item.owner.reputation.prettyCount()
                timeAgoText.text = item.creationDate.humanTime(containerView.resources)

                Glide.with(imageProfile)
                    .load(item.owner.profileImage)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageProfile)

                profileContainer.clicks()
                    .filterRapidClicks()
                    .subscribe { clickListenerProfile(item) }
                    .disposedBy(subscriptions)

            }
        }

        private fun TextView.setTextColor(isAnswered: Boolean) {
            this.setTextColor(when {
                isAnswered -> ContextCompat.getColor(containerView.context, android.R.color.holo_green_dark)
                else -> ContextCompat.getColor(containerView.context, android.R.color.darker_gray)
            }
            )
        }

        //TODO: move to mapper
        private fun String.humanText(): String =
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
                }
                else -> {
                    Html.fromHtml(this).toString()
                }
            }
    }
}