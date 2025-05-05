package com.example.lab2barde

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    private var iosCountFromMainActivity = 0
    private var androidCountFromMainActivity = 0
    private lateinit var etAndroid: EditText
    private lateinit var etIos: EditText
    private lateinit var btnReturnToMain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        initViews()
        getDataFromBundle()
        handleReturnToMain()
    }

    private fun initViews() {
        etAndroid = findViewById(R.id.etAndroid)
        etIos = findViewById(R.id.etIos)
        btnReturnToMain = findViewById(R.id.btnReturnToMain)
    }

    private fun handleReturnToMain() {
        btnReturnToMain.setOnClickListener {
            val androidInput = etAndroid.text.toString()
            val iosInput = etIos.text.toString()
            val bundle = Bundle()
            val sharedPref = getSharedPreferences("filename", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("androidKey",androidInput)
            editor.apply()
            editor.putString("iosKey",iosInput)
            editor.apply()
            bundle.putString("androidInput", androidInput)
            bundle.putString("iosInput", iosInput)
            val intent = Intent()
            intent.putExtras(bundle)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun getDataFromBundle() {
        androidCountFromMainActivity = intent.extras?.getInt("android", 0) ?: 0
        etAndroid.setText(androidCountFromMainActivity.toString())

        iosCountFromMainActivity = intent.extras?.getInt("ios", 0) ?: 0
        etIos.setText(iosCountFromMainActivity.toString())

    }
}