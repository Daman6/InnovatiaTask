package com.example.innovatiatask.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionModel (
    var question:String,
    var totalquestionNo:String,
    @PrimaryKey(autoGenerate = false)
    var currentquestionNo:String,
    var correctAns:String,
    var selectedOption:String,
    var option1:String,
    var option2:String,
    var option3:String,
    var option4:String,
    var option1Image:String,
    var option2Image:String,
    var option3Image:String,
    var option4Image:String
    ){
}