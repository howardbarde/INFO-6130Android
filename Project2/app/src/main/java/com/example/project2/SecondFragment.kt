package com.example.project2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SecondFragment : Fragment() {

    private lateinit var btnMap: Button
    private lateinit var btnEmail: Button
    private lateinit var btnSMS: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        btnMap = view.findViewById(R.id.btnMap)
        btnEmail = view.findViewById(R.id.btnEmail)
        btnSMS = view.findViewById(R.id.btnSMS)

        handleButtonPress()

        return view
    }

    private fun handleButtonPress() {
        btnSMS.setOnClickListener {
            val smsFragment = SmsFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fcvMain, smsFragment)  // Replace the fragment
            transaction.commit()
        }

        btnEmail.setOnClickListener {
            val emailFragment = EmailFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fcvMain, emailFragment)  // Replace the fragment
            transaction.commit()
        }

        btnMap.setOnClickListener {
            val mapFragment = MapFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fcvMain, mapFragment)  // Replace the fragment
            transaction.commit()
        }
    }


}