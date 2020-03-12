package com.koidev.stackoverflowstars.ui.list

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding3.view.clicks
import com.koidev.domain.Question
import com.koidev.domain.common.disposedBy
import com.koidev.stackoverflowstars.R
import com.koidev.stackoverflowstars.utils.diff.QuestionsDiffUtil
import com.koidev.stackoverflowstars.utils.filterRapidClicks
import com.koidev.stackoverflowstars.utils.humanTime
import com.koidev.stackoverflowstars.utils.prettyCount
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_question.view.*

class HistoryQuestionAdapter(
    private val data: MutableList<Question>,
    private val onClickItem: (question: Question) -> Unit,
    private val onClickImage: (question: Question) -> Unit,
    private val subscriptions: CompositeDisposable
) : RecyclerView.Adapter<HistoryQuestionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )

    override fun getItemCount(): Int = data.size

    fun replace(newList: List<Question>) {
        val callback = QuestionsDiffUtil(data, newList)
        val result = DiffUtil.calculateDiff(callback)
        data.clear()
        data.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            item = data[position],
            onClickItem = onClickItem,
            onClickImage = onClickImage,
            subscriptions = subscriptions
        )
    }

    class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private lateinit var item: Question

        fun bind(
            item: Question,
            onClickItem: (Question) -> Unit,
            onClickImage: (Question) -> Unit,
            subscriptions: CompositeDisposable
        ) {
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

                containerView.clicks()
                    .filterRapidClicks()
                    .subscribe { onClickItem(item) }
                    .disposedBy(subscriptions)

                profileContainer.clicks()
                    .filterRapidClicks()
                    .subscribe { onClickImage(item) }
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