package com.example.innovatiatask.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.innovatiatask.R
import com.example.innovatiatask.databinding.ItemViewpagerBinding
import com.example.innovatiatask.databinding.ResultItemViewpagerBinding
import com.example.innovatiatask.model.QuestionModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ResultViewpagerAdapter(
    val list: List<QuestionModel>,val resultan: Int,val right: Int,val wrong: Int
) :
    RecyclerView.Adapter<ResultViewpagerAdapter.ViewPagerHolder>() {

    var result = 0


    inner class ViewPagerHolder(val binding: ResultItemViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(questionModel: QuestionModel) {
            binding.questionmodel = questionModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding: ResultItemViewpagerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.result_item_viewpager, parent, false
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


        holder.binding.totalMarksT.text = resultan.toString()+" / ${currentItem.totalquestionNo.toString()}"
        holder.binding.rightT.text = right.toString()


        checkAnswer(currentItem.correctAns,currentItem.selectedOption.toString(),holder.binding.linear1,holder.binding.linear2,holder.binding.linear3,holder.binding.linear4)



    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun checkAnswer(correctOption:String , selectedOptionPos : String,option1:RelativeLayout,option2:RelativeLayout,option3: RelativeLayout,option4: RelativeLayout):Boolean{
        if (correctOption.equals(selectedOptionPos)){
            result++
            if (selectedOptionPos.equals("1")){
                option1.isSelected=true
                option1.isActivated=true
            }else if (selectedOptionPos.equals("2")){
                option2.isSelected=true
                option2.isActivated=true
            }else if (selectedOptionPos.equals("3")){
                option3.isSelected=true
                option3.isActivated=true
            }else{
                option4.isSelected=true
                option4.isActivated=true
            }
            return true
        }else if(!correctOption.equals(selectedOptionPos)){
            if (correctOption.equals("1")){
                option1.isSelected=true
                option1.isActivated=true
            }else if (correctOption.equals("2")){
                option2.isSelected=true
                option2.isActivated=true
            }else if (correctOption.equals("3")){
                option3.isSelected=true
                option3.isActivated=true
            }else{
                option4.isSelected=true
                option4.isActivated=true
            }
            if (selectedOptionPos.equals("1")){
                option1.isSelected=true
                option1.isActivated=false
            }else if (selectedOptionPos.equals("2")){
                option2.isSelected=true
                option2.isActivated=false
            }else if (selectedOptionPos.equals("3")){
                option3.isSelected=true
                option3.isActivated=false
            }else{
                option4.isSelected=true
                option4.isActivated=false
            }
            return false
        }
        return false
    }

}