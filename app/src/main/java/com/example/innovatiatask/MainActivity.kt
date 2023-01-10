package com.example.innovatiatask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innobuzztask.viewModel.MainViewModelProviderFactory
import com.example.innovatiatask.adapter.QuizNoRecyAdapter
import com.example.innovatiatask.adapter.ViewpagerAdapter
import com.example.innovatiatask.databinding.ActivityMainBinding
import com.example.innovatiatask.model.QuestionModel
import com.example.innovatiatask.repo.Repo
import com.example.innovatiatask.utils.util
import com.example.innovatiatask.viewModel.MainViewModel
import com.example.newsapi.db.QuestionDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}