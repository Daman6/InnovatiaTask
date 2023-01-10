package com.example.innovatiatask.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.innovatiatask.model.QuestionModel
import com.example.innovatiatask.repo.Repo
import com.example.newsapi.db.QuestionDatabase
import kotlinx.coroutines.launch

class MainViewModel(val repo: Repo ,val context: Application) : AndroidViewModel(context) {

    var resultList : List<QuestionModel>

    init {
        resultList =  repo.getAll()
    }
    fun saveQuestion(questionModel: QuestionModel) {
        viewModelScope.launch {
            repo.insert(questionModel)
        }
    }

    fun upsertOption(selectedOption: String,currentquestionNo:String) {
        viewModelScope.launch {
            repo.upsert(selectedOption,currentquestionNo)
        }
    }

    fun getEmptyQuiz(currentquestionNo: String):String{
        return repo.getEmptyQuiz(currentquestionNo)
    }
}