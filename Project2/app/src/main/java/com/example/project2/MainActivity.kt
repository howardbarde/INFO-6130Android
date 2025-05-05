package com.example.project2

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnSms: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //initViews()
    }

    private fun initViews() {
        btnSms = findViewById(R.id.btnSMS)
        btnSms.setOnClickListener {
            val smsFragment = SmsFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fcvMain, smsFragment)
            transaction.commit()
        }
    }
}