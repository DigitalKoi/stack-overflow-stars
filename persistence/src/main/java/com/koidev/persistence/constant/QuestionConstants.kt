package com.koidev.persistence.constant

object QuestionConstants {

    const val QUESTIONS_TABLE_NAME = "Question"

    const val QUERY_QUESTIONS = "SELECT * FROM $QUESTIONS_TABLE_NAME"
    const val CLEAR_QUESTIONS = "DELETE FROM $QUESTIONS_TABLE_NAME"
}