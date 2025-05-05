package com.example.lab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab1.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun updateDetails(level: Int) {
        val details = resources.getStringArray(R.array.happiness_details)
        val lvl = resources.getStringArray(R.array.happiness_levels)
        binding.tvDetails.text = details[level]
        binding.tvLevelHeader.text = lvl[level]
    }
}
