package com.example.innovatiatask.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innobuzztask.viewModel.MainViewModelProviderFactory
import com.example.innovatiatask.R
import com.example.innovatiatask.adapter.QuizNoRecyAdapter
import com.example.innovatiatask.adapter.ResultQuizNoRecyAdapter
import com.example.innovatiatask.adapter.ResultViewpagerAdapter
import com.example.innovatiatask.adapter.ViewpagerAdapter
import com.example.innovatiatask.databinding.FragmentQuizBinding
import com.example.innovatiatask.databinding.FragmentResultBinding
import com.example.innovatiatask.model.QuestionModel
import com.example.innovatiatask.repo.Repo
import com.example.innovatiatask.utils.util
import com.example.innovatiatask.viewModel.MainViewModel
import com.example.newsapi.db.QuestionDatabase


class ResultFragment : Fragment() ,ResultQuizNoRecyAdapter.ButtonClick {
    private lateinit var binding : FragmentResultBinding
    private lateinit var adapter: ResultViewpagerAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var quizAdapter: ResultQuizNoRecyAdapter
    private lateinit var resultList: List<QuestionModel>
    private lateinit var quizNoList: MutableList<Int>
    private var result : Int = 0
    private var totalWrong : Int = 0
    private var totalRight : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repo = Repo(QuestionDatabase(requireContext()))
        val viewModelProviderFactory = MainViewModelProviderFactory(repo,requireActivity().application)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(MainViewModel::class.java)


        resultList = mutableListOf()
        resultList = viewModel.resultList



        resultList.forEach {
            if (it.correctAns.equals(it.selectedOption)) {
                result++
                totalRight++
            }else{
                totalWrong++
            }
        }

        adapter = ResultViewpagerAdapter(resultList,result,totalRight,totalWrong)
        binding.quizviewpager2.adapter = adapter

        quizNoList = mutableListOf()
        for(i in 1..util().questionList().size) {
            quizNoList.add(i)
        }


        quizAdapter =  ResultQuizNoRecyAdapter(resultList,this)
        binding.totalRecy.adapter = quizAdapter
        binding.totalRecy.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)

        binding.nextBtn.setOnClickListener {
            binding.quizviewpager2.setCurrentItem(binding.quizviewpager2.getCurrentItem() + 1);
        }

        binding.previousBtn.setOnClickListener {
            binding.quizviewpager2.setCurrentItem(binding.quizviewpager2.getCurrentItem() - 1);
        }
    }

    override fun onClick(position: Int) {
        binding.quizviewpager2.setCurrentItem(position-1)
    }

}