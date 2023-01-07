package com.example.innovatiatask.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.innovatiatask.R
import com.example.innovatiatask.databinding.QuizNoItemLayoutBinding

class QuizNoRecyAdapter(private val mlist: MutableList<Int>, val buttonClick: ButtonClick) :
    RecyclerView.Adapter<QuizNoRecyAdapter.QuizNoViewHolder>() {

    class QuizNoViewHolder(var binding: QuizNoItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizNoViewHolder {
        val binding: QuizNoItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.quiz_no_item_layout,
            parent,
            false
        )
        return QuizNoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizNoViewHolder, position: Int) {
        val current = mlist[position]
        holder.binding.quizNot.text = current.toString()

        holder.itemView.setOnClickListener {
            buttonClick.onClick(current)
        }
    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    interface ButtonClick {
        fun onClick(position: Int) {}
    }
}


