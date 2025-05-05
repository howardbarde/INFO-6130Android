package com.example.lab1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.databinding.ItemLevelBinding


class LevelAdapter(
    private val levels: Array<String>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<LevelAdapter.ViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLevelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(levels[position], position)
    }

    override fun getItemCount(): Int = levels.size

    inner class ViewHolder(private val binding: ItemLevelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(level: String, position: Int) {
            binding.levelName.text = level

            if (position == selectedPosition) {
                binding.root.setBackgroundColor(binding.root.context.getColor(android.R.color.holo_blue_light))  // Change color when selected
            } else {
                binding.root.setBackgroundColor(binding.root.context.getColor(android.R.color.transparent))  // Default background
            }

            binding.root.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                onClick(position)
            }
        }
    }
}

