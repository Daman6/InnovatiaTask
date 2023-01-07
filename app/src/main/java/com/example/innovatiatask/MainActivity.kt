package com.example.innovatiatask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innovatiatask.adapter.QuizNoRecyAdapter
import com.example.innovatiatask.adapter.ViewpagerAdapter
import com.example.innovatiatask.databinding.ActivityMainBinding
import com.example.innovatiatask.model.QuestionModel

class MainActivity : AppCompatActivity(),QuizNoRecyAdapter.ButtonClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViewpagerAdapter
    private lateinit var quizAdapter: QuizNoRecyAdapter
    private lateinit var list: MutableList<QuestionModel>
    private lateinit var quizNoList: MutableList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = mutableListOf()
        list.add(QuestionModel("Captial Of India?","8","1","Mumbai","Jaipur","Delhi","Kolkata"))
        list.add(QuestionModel("Who Won Fifa 2022?","8","2","Argentina","India","Spain","England"))
        list.add(QuestionModel("Name Sharukh khan first movie","8","3","Deewana","Dilwale","Darr","Dil se"))
        list.add(QuestionModel("Which is an Indian currency","8","4","INR","pounds","Dollar","Dhiram"))
        list.add(QuestionModel("Which festival is known as festival of colours","8","5","Diwali","Onam","Holi","Christmas"))
        list.add(QuestionModel("Which festival is known as festival of lights","8","6","Diwali","Onam","Holi","Christmas"))
        list.add(QuestionModel("Steve job is the owner of whicg of the following company","8","7","Apple","Samsung","Oppo","OnePlus"))
        list.add(QuestionModel("Who is SIDHU MOOSE WALA ","8","8","LEGEND","LEGEND","LEGEND","LEGEND"))

        adapter = ViewpagerAdapter(list)
        binding.quizviewpager2.adapter = adapter

        quizNoList = mutableListOf()
        for(i in 1..list.size) {
            quizNoList.add(i)
        }


        quizAdapter =  QuizNoRecyAdapter(quizNoList,this)
        binding.totalRecy.adapter = quizAdapter
        binding.totalRecy.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        binding.nextBtn.setOnClickListener {
            binding.quizviewpager2.setCurrentItem(binding.quizviewpager2.getCurrentItem() + 1);
        }

        binding.previousBtn.setOnClickListener {
            binding.quizviewpager2.setCurrentItem(binding.quizviewpager2.getCurrentItem() - 1);
        }



    }

    override fun onClick(position: Int) {
        super.onClick(position)
        binding.quizviewpager2.setCurrentItem(position)
    }
}