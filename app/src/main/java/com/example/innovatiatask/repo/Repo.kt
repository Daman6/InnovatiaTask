package com.example.innovatiatask.repo

import androidx.lifecycle.viewModelScope
import com.example.innovatiatask.model.QuestionModel
import com.example.newsapi.db.QuestionDatabase
import kotlinx.coroutines.launch

class Repo (val db : QuestionDatabase){
    suspend fun insert(questionModel: QuestionModel) = db.getUserDao().insert(questionModel)

    suspend fun upsert(selectedOption: String,currentquestionNo:String) = db.getUserDao().upsert(selectedOption,currentquestionNo)


    fun getAll():List<QuestionModel> = db.getUserDao().getList()

    fun getEmptyQuiz(currentquestionNo: String):String = db.getUserDao().getEmptyQuiz(currentquestionNo)
}