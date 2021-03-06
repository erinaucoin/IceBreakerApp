package com.erinaucoin.IceBreaker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionListAdapter internal constructor(context: Context):
RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var questions = emptyList<Question>() // Cached copy of questions

    inner class QuestionViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val questionItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return QuestionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val current = questions[position]
        holder.questionItemView.text = current.question
    }

    internal fun setQuestions(questions: List<Question>){
        this.questions = questions
        notifyDataSetChanged()
    }

    override fun getItemCount() = questions.size
}