package com.example.newsapi.db

import androidx.room.*
import com.example.innovatiatask.model.QuestionModel

@androidx.room.Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(questionModel: QuestionModel):Long

    @Query("UPDATE QuestionModel SET selectedOption=:selectedOption WHERE currentquestionNo == :currentquestionNo")
    suspend fun upsert(selectedOption: String,currentquestionNo:String)

    @Query("SELECT * FROM QuestionModel")
    fun getList():List<QuestionModel>

    @Query("SELECT selectedOption FROM QuestionModel WHERE currentquestionNo=:currentquestionNo ")
    fun getEmptyQuiz(currentquestionNo:String):String

//    @Query("Select * From articles")
//    fun getAllArticles():LiveData<List<Article>>
//
//    @Delete
//    suspend fun deleteArticle(article: Article)
}