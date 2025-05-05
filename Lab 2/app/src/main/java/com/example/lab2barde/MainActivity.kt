package com.example.lab2barde

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var btnAndroid: Button
    private lateinit var btnIos: Button
    private lateinit var btnResults: Button
    private lateinit var androidTv: TextView
    private lateinit var iosTv: TextView
    private var androidCounter: Int = 0
    private var iosCounter: Int = 0
    private var iosCountFromSecondActivity = ""
    private var androidCountFromSecondActivity = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initViews()
        handleButtonPress()
        //handleResultsPress()
        handleReceiveButtonPress()
    }

    override fun onResume() {
        super.onResume()
        val sharedpref = getSharedPreferences("filename", Context.MODE_PRIVATE)
        androidTv.text = sharedpref.getString("androidKey","0")
        androidCountFromSecondActivity = sharedpref.getString("androidKey","0").toString()
        androidCounter = androidCountFromSecondActivity.toInt()
        iosTv.text = sharedpref.getString("iosKey","0")
        iosCountFromSecondActivity = sharedpref.getString("androidKey","0").toString()
        iosCounter = iosCountFromSecondActivity.toInt()
    }

    private fun initViews() {
        btnAndroid = findViewById(R.id.btnAndroid)
        btnIos = findViewById(R.id.btnIos)
        btnResults = findViewById(R.id.btnResults)
        androidTv = findViewById(R.id.androidTv)
        iosTv = findViewById(R.id.iosTv)
    }

    private fun handleResultsPress() {
        btnResults.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt("ios", iosCounter)
            bundle.putInt("android", androidCounter)

            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun handleButtonPress() {

        btnAndroid.setOnClickListener {
            androidCounter++
            androidTv.text = androidCounter.toString()
        }

        btnIos.setOnClickListener {
            iosCounter++
            iosTv.text = iosCounter.toString()
        }
    }

    private fun handleReceiveButtonPress() {
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                when (activityResult.resultCode) {
                    RESULT_OK -> {
                        val androidResult = activityResult.data?.extras?.getString("androidInput") ?: ""
                        val iosResult = activityResult.data?.extras?.getString("iosInput") ?: ""
                        androidCounter = androidResult.toInt()
                        iosCounter = iosResult.toInt()
                        androidTv.text = androidResult
                        iosTv.text = iosResult
                    }
                }
            }



        btnResults.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("ios", iosCounter)
                putInt("android", androidCounter)
            }

            val intent = Intent(this@MainActivity, SecondActivity::class.java).apply {
                putExtras(bundle)
            }
            resultLauncher.launch(intent)
//            val intent2 = Intent(this@MainActivity, SecondActivity::class.java)
//            resultLauncher.launch(intent2)
//
//            val bundle = Bundle()
//            bundle.putInt("ios", iosCounter)
//            bundle.putInt("android", androidCounter)
//
//            val intent = Intent(this@MainActivity, SecondActivity::class.java)
//            intent.putExtras(bundle)
//            startActivity(intent)
        }
    }

}