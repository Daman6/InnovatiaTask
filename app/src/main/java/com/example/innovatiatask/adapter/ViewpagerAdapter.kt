package com.example.innovatiatask.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.innovatiatask.R
import com.example.innovatiatask.databinding.ItemViewpagerBinding
import com.example.innovatiatask.model.QuestionModel

class ViewpagerAdapter(val list :MutableList<QuestionModel>):
    RecyclerView.Adapter<ViewpagerAdapter.ViewPagerHolder>() {

    inner class ViewPagerHolder(val binding: ItemViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(questionModel: QuestionModel) {
            binding.questionmodel = questionModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding: ItemViewpagerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_viewpager, parent, false
        )
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val currentItem = list[position]
        holder.bind(currentItem)
        holder.binding.apply {
            option1.apply {
                setOnClickListener {
                    if (it.isSelected){
                        holder.binding.option1.isSelected = false

                    }else{
                        holder.binding.option1.isSelected = true
                        holder.binding.option2.isSelected = false
                        holder.binding.option3.isSelected = false
                        holder.binding.option4.isSelected = false
                    }
                }
            }
            option2.apply {
                setOnClickListener {
                    if (it.isSelected){
                        holder.binding.option2.isSelected = false
                    }else{
                        holder.binding.option1.isSelected = false
                        holder.binding.option2.isSelected = true
                        holder.binding.option3.isSelected = false
                        holder.binding.option4.isSelected = false                    }
                }
            }
            option3.apply {
                setOnClickListener {
                    if (it.isSelected){
                        holder.binding.option3.isSelected = false
                    }else{
                        holder.binding.option1.isSelected = false
                        holder.binding.option2.isSelected = false
                        holder.binding.option3.isSelected = true
                        holder.binding.option4.isSelected = false                    }
                }
            }
            option4.apply {
                setOnClickListener {
                    if (it.isSelected){
                        holder.binding.option4.isSelected = false
                    }else{
                        holder.binding.option1.isSelected = false
                        holder.binding.option2.isSelected = false
                        holder.binding.option3.isSelected = false
                        holder.binding.option4.isSelected = true                    }
                }
            }
        }

//        holder.binding.question.text = currentItem.question.toString()
//        holder.binding.option1.text = currentItem.option1.toString()
//        holder.binding.option2.text = currentItem.option2.toString()
//        holder.binding.option3.text = currentItem.option3.toString()
//        holder.binding.option4.text = currentItem.totalquestionNo.toString()+"/"+currentItem.currentquestionNo.toString()
    }

    override fun getItemCount(): Int {
       return list.size
    }
}