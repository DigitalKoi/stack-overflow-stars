package com.koidev.stackoverflowstars.utils.diff

import androidx.recyclerview.widget.DiffUtil
import com.koidev.domain.Question

class QuestionsDiffUtil(
    private val old: List<Question>,
    private val new: List<Question>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].questionId == new[newItemPosition].questionId
    }

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}