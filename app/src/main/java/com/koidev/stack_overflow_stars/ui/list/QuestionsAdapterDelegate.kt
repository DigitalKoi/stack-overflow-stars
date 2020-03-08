package com.koidev.stack_overflow_stars.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.koidev.domain.Question
import com.koidev.stack_overflow_stars.R
import com.koidev.stack_overflow_stars.utils.inflate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_question.view.*

fun Question.isSame(other: Question) = questionId == other.questionId

class QuestionsAdapterDelegate(
    private val clickListener: (Question) -> Unit
) : AdapterDelegate<MutableList<Any>>() {


    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean =
        items[position] is Question

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val root = parent.inflate(R.layout.item_question)

        return ViewHolder(root)
    }

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        viewHolder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (viewHolder as ViewHolder).bind(items[position] as Question)


    private inner class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private lateinit var item: Question

        init {
            containerView.setOnClickListener { clickListener(item) }
        }

        fun bind(item: Question) {
            this.item = item
            containerView.apply {
                userName.text = item.owner.displayName
                question.text = item.title
                answerCount.text = item.answerCount.toString()
                Glide.with(userImage)
                    .load(item.owner.profileImage)
                    .apply(RequestOptions.circleCropTransform())
                    .into(userImage)

                statusIcon.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        if (item.isAnswered) R.drawable.ic_question_green else R.drawable.ic_question_grey,
                        null
                    )
                )
            }
        }
    }
}