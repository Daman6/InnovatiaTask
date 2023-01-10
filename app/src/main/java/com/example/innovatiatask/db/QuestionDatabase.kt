package com.example.newsapi.db

import android.content.Context
import androidx.room.*
import com.example.innovatiatask.model.QuestionModel

@Database(
    entities = [QuestionModel::class],
    version = 5,
    exportSchema = false
)

abstract class QuestionDatabase : RoomDatabase() {

    abstract fun getUserDao(): Dao

    companion object{

        private var instance: QuestionDatabase? = null
        private val lock= Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock)
        {
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                QuestionDatabase::class.java,
                "question_db.db"
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()


    }
}