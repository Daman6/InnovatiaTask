package com.example.innovatiatask.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.innovatiatask.R
import com.example.innovatiatask.databinding.ItemViewpagerBinding
import com.example.innovatiatask.model.QuestionModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ViewpagerAdapter(
    val list: MutableList<QuestionModel>,
    val buttonClick: ButtonClick
) :
    RecyclerView.Adapter<ViewpagerAdapter.ViewPagerHolder>() {

    var selectedOp = MutableLiveData<String>("")

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


        Picasso.get().load(currentItem.option1Image.toString()).placeholder(R.drawable.loading).into(holder.binding.option1Image)
        Picasso.get().load(currentItem.option2Image.toString()).placeholder(R.drawable.loading).into(holder.binding.option2Image)
        Picasso.get().load(currentItem.option3Image.toString()).placeholder(R.drawable.loading).into(holder.binding.option3Image)
        Picasso.get().load(currentItem.option4Image.toString()).placeholder(R.drawable.loading).into(holder.binding.option4Image)



        holder.binding.linear1.setOnClickListener {
            holder.binding.linear1.isSelected = false
            if (holder.binding.linear1.isSelected) {
                holder.binding.linear1.isSelected = false
            } else {
                holder.binding.linear1.isSelected = true
                holder.binding.linear2.isSelected = false
                holder.binding.linear3.isSelected = false
                holder.binding.linear4.isSelected = false
            }
            selectedOp.value = "1"
            buttonClick.onClick(selectedOp.value.toString(),currentItem.currentquestionNo,holder.binding)

        }
        holder.binding.linear2.setOnClickListener {
            holder.binding.linear2.isSelected = false
            if (holder.binding.linear2.isSelected) {
                holder.binding.linear2.isSelected = false
            } else {
                holder.binding.linear1.isSelected = false
                holder.binding.linear2.isSelected = true
                holder.binding.linear3.isSelected = false
                holder.binding.linear4.isSelected = false
            }
            selectedOp.value = "2"
            buttonClick.onClick(selectedOp.value.toString(),currentItem.currentquestionNo,holder.binding)


        }

        holder.binding.linear3.setOnClickListener {
            holder.binding.linear3.isSelected = false
            if (holder.binding.linear3.isSelected) {
                holder.binding.linear3.isSelected = false
            } else {
                holder.binding.linear1.isSelected = false
                holder.binding.linear2.isSelected = false
                holder.binding.linear3.isSelected = true
                holder.binding.linear4.isSelected = false
            }
            selectedOp.value = "3"
            buttonClick.onClick(selectedOp.value.toString(),currentItem.currentquestionNo,holder.binding)


//                    checkAnswer(currentItem.correctAns.toString(),"3",holder.binding.option3,holder.binding.option1,holder.binding.option2,holder.binding.option3,holder.binding.option4)
//                    holder.binding.option1.isEnabled = false
//                    holder.binding.option2.isEnabled = false
//                    holder.binding.option3.isEnabled = false
//                    holder.binding.option4.isEnabled = false

        }

        holder.binding.linear4.setOnClickListener {
            holder.binding.linear4.isSelected = false
            if (holder.binding.linear4.isSelected) {
                holder.binding.linear4.isSelected = false
            } else {
                holder.binding.linear1.isSelected = false
                holder.binding.linear2.isSelected = false
                holder.binding.linear3.isSelected = false
                holder.binding.linear4.isSelected = true
            }
            selectedOp.value = "4"
            buttonClick.onClick(selectedOp.value.toString(),currentItem.currentquestionNo,holder.binding)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ButtonClick {
        fun onClick(selectedOp: String,currentQuesNo:String,binding: ItemViewpagerBinding)

    }

}