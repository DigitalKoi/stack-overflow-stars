package com.koidev.stackoverflowstars.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
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
                userName.text = item.owner.displayName
                question.text = item.title
                answerCount.text = item.answerCount.toString()
                Glide.with(userImage)
                    .load(item.owner.profileImage)
                    .apply(RequestOptions.circleCropTransform())
                    .into(userImage)

                statusIcon.apply {
                    setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            if (item.isAnswered) R.drawable.ic_question_green else R.drawable.ic_question_grey,
                            null
                        )
                    )
                }

                containerView.clicks()
                    .filterRapidClicks()
                    .subscribe { onClickItem(item) }
                    .disposedBy(subscriptions)

                userImage.clicks()
                    .filterRapidClicks()
                    .subscribe { onClickImage(item) }
                    .disposedBy(subscriptions)

            }
        }
    }
}