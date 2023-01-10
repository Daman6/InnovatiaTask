package com.example.innovatiatask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.innobuzztask.viewModel.MainViewModelProviderFactory
import com.example.innovatiatask.R
import com.example.innovatiatask.adapter.QuizNoRecyAdapter
import com.example.innovatiatask.adapter.ViewpagerAdapter
import com.example.innovatiatask.databinding.FragmentQuizBinding
import com.example.innovatiatask.databinding.ItemViewpagerBinding
import com.example.innovatiatask.model.QuestionModel
import com.example.innovatiatask.repo.Repo
import com.example.innovatiatask.utils.util
import com.example.innovatiatask.viewModel.MainViewModel
import com.example.newsapi.db.QuestionDatabase


class QuizFragment : Fragment(), QuizNoRecyAdapter.ButtonClick, ViewpagerAdapter.ButtonClick {
    private lateinit var binding: FragmentQuizBinding
    private lateinit var adapter: ViewpagerAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var quizAdapter: QuizNoRecyAdapter
    private lateinit var list: MutableList<QuestionModel>
    private lateinit var quizNoList: MutableList<Int>


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
        binding = FragmentQuizBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repo = Repo(QuestionDatabase(requireContext()))
        val viewModelProviderFactory =
            MainViewModelProviderFactory(repo, requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        list = mutableListOf()

        util().questionList().forEach {
            viewModel.saveQuestion(it)
        }
        adapter = ViewpagerAdapter(util().questionList(), this)
        binding.quizviewpager2.adapter = adapter

        quizNoList = mutableListOf()
        for (i in 1..util().questionList().size) {
            quizNoList.add(i)
        }


        quizAdapter = QuizNoRecyAdapter(util().questionList(), this)
        binding.totalRecy.adapter = quizAdapter
        binding.totalRecy.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )

        binding.nextBtn.setOnClickListener {
            binding.quizviewpager2.setCurrentItem(binding.quizviewpager2.getCurrentItem() + 1);
        }

        binding.previousBtn.setOnClickListener {
            binding.quizviewpager2.setCurrentItem(binding.quizviewpager2.getCurrentItem() - 1);
        }

        binding.submitTv.setOnClickListener {
//            viewModel.resultList.forEach {
//                if (it.selectedOption.equals("")){
//                    Toast.makeText(requireContext(),"All Questions Are Not Done!",Toast.LENGTH_SHORT).show()
//                }
//                showDailogBox()
//            }
            if (viewModel.getEmptyQuiz("1").equals("")) {
                Toast.makeText(requireContext(), "Question 1 is not completed", Toast.LENGTH_LONG).show()
            }else if (viewModel.getEmptyQuiz("2").equals("")){
                Toast.makeText(requireContext(), "Question 2 is not completed", Toast.LENGTH_LONG).show()
            }
            else if (viewModel.getEmptyQuiz("3").equals("")){
                Toast.makeText(requireContext(), "Question 3 is not completed", Toast.LENGTH_LONG).show()
            }
            else if (viewModel.getEmptyQuiz("4").equals("")){
                Toast.makeText(requireContext(), "Question 4 is not completed", Toast.LENGTH_LONG).show()
            }
            else if (viewModel.getEmptyQuiz("5").equals("")){
                Toast.makeText(requireContext(), "Question 5 is not completed", Toast.LENGTH_LONG).show()
            }else{
                showDailogBox()
            }
        }
    }


    override fun onClick(position: Int) {
        binding.quizviewpager2.setCurrentItem(position - 1)
    }

    override fun onClick(selectedOp: String, currentQuesNo: String, binding: ItemViewpagerBinding) {
        viewModel.upsertOption(selectedOp, currentQuesNo)

    }


    private fun showDailogBox(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Submit Quiz !!")
        builder.setMessage("Are you sure you want to submit")
        builder.setCancelable(false)

        builder.setPositiveButton("yes") { dialog, which ->
            findNavController().navigate(R.id.action_quizFragment_to_resultFragment)
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
            Toast.makeText(requireContext(), "No", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }


}