package com.example.lovecalculate.main.fragment.adapter

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lovecalculate.databinding.ItemHistoryBinding
import com.example.lovecalculate.model.LoveModel
import java.util.*

class HistoryAdapter(val onCLick:(LoveModel)->Unit):ListAdapter<LoveModel, HistoryAdapter.ViewHolder>(Diff()) {
    private var items: MutableList<LoveModel> = mutableListOf()
    inner class ViewHolder(private val binding:ItemHistoryBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: LoveModel) {
            binding.tvFirstName.text = model.firstName
            binding.tvSecondName.text = model.secondName
            binding.tvScore.text = model.percentage
            binding.tvScoreText.text = model.result


            itemView.setOnLongClickListener {
                onCLick(model)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


class Diff:DiffUtil.ItemCallback<LoveModel>() {
    override fun areItemsTheSame(oldItem: LoveModel, newItem: LoveModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: LoveModel, newItem: LoveModel): Boolean {
        return oldItem == newItem
    }

}
