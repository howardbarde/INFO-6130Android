package com.example.lab1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.lab1.databinding.ActivityInstructionsBinding

class InstructionsActivity : AppCompatActivity(), Icomm {

    private lateinit var binding: ActivityInstructionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstructionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val levelFragment = LevelFragment()
        val detailFragment = DetailFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.level_container, levelFragment)
            .replace(R.id.detail_container, detailFragment)
            .commit()
    }

    override fun onLevelSelected(level: Int) {
        val fragment = supportFragmentManager.findFragmentById(R.id.detail_container) as DetailFragment
        fragment.updateDetails(level)
    }
}
