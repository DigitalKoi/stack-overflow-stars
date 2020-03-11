package com.koidev.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.koidev.persistence.constant.Config
import com.koidev.persistence.dao.QuestionsDao
import com.koidev.persistence.model.CachedQuestions
import com.koidev.persistence.utils.Converters


@Database(
    entities = [
        (CachedQuestions::class)
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class StackOverFlowDatabase : RoomDatabase() {

    abstract fun questionsDao(): QuestionsDao

    companion object {

        private var INSTANCE: StackOverFlowDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): StackOverFlowDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = getDB(context)
                    }
                    return INSTANCE as StackOverFlowDatabase
                }
            }

            return INSTANCE as StackOverFlowDatabase
        }

        private fun getDB(context: Context): StackOverFlowDatabase =
            Room
                .databaseBuilder(
                    context.applicationContext,
                    StackOverFlowDatabase::class.java,
                    Config.DATABASE_NAME
                )
                .fallbackToDestructiveMigration()
                .build()
    }
}