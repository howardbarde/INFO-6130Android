package com.example.lab1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import com.example.lab1.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, ICommunicator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.instruction.setOnClickListener(this)
        binding.survey.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        when (view?.id) {
            R.id.instruction -> {
                val intent = Intent(this, InstructionsActivity::class.java)
                startActivity(intent)
            }

            R.id.survey -> {
                val confirmFrag: DialogFragment? = TakeSurvey.newInstance(R.string.take_survey)
                confirmFrag?.show(supportFragmentManager, "dialog")
            }
        }
    }

    override fun choiceMade(msg: Int?) {
        binding.tvResult.text = "Happiness level is: ${TakeSurvey.levels[msg!!]}"
        TakeSurvey.checkedItem = msg
    }

}