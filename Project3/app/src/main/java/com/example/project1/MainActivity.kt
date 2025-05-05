package com.example.project1

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var roverAdapter: RoverExpandableAdapter
    private val viewModel: RoverViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        roverAdapter = RoverExpandableAdapter(mutableListOf())
        binding.recyclerView.adapter = roverAdapter

        viewModel.rovers.observe(this) { roverList ->
            roverList?.let {
                val roverParents = it.map { rover -> RoverParent(rover) }.toMutableList()
                roverAdapter.updateData(roverParents)
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.fetchRovers()
    }
}
