package com.example.project2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SmsFragment : Fragment() {
    private lateinit var smsEditText: EditText
    private lateinit var sendSmsButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment's layout
        val view = inflater.inflate(R.layout.fragment_sms, container, false)

        // Initialize views
        smsEditText = view.findViewById(R.id.smsEditText)
        sendSmsButton = view.findViewById(R.id.sendSmsButton)

        // Set the button click listener
        sendSmsButton.setOnClickListener {
            val emailAddress = smsEditText.text.toString().trim()

            if (emailAddress.isNotEmpty()) {
                requestPermission()
                //requestPermissionDirect()
            } else {
                // Optionally, show an error message if email is empty
                smsEditText.error = "Please enter a valid Phone number"
            }
        }

        return view
    }

    private fun requestPermission() {
        // Inside a Fragment

        when {
            // Check if the permission is granted
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED -> {
                // Permission is granted
                Toast.makeText(requireContext(), getString(R.string.per_grant), Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("smsto:")  // Ensures only SMS apps respond
                    putExtra("address", smsEditText.text.toString().trim()) // Set the phone number
                    putExtra("sms_body", getString(R.string.sms)) // Set the SMS body
                }
                // Start the activity using the SMS chooser
                startActivity(Intent.createChooser(intent, "SMS"))
            }

            // Check if we should show rationale for requesting permission
            ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.SEND_SMS) -> {
                // Additional rationale is displayed
                Toast.makeText(requireContext(), getString(R.string.per_not_grant), Toast.LENGTH_SHORT).show()
                // Request the permission using the launcher
                requestPermissionLauncher.launch(Manifest.permission.SEND_SMS)
            }

            // If the permission has not been asked yet
            else -> {
                // Permission has not been asked yet
                Toast.makeText(requireContext(), getString(R.string.per_not_ask), Toast.LENGTH_SHORT).show()
                // Request the permission using the launcher
                requestPermissionLauncher.launch(Manifest.permission.SEND_SMS)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result:Boolean ->
        // checking the result of permission
        if (result) {
            //Toast.makeText(this,getString(R.string.per_grant), Toast.LENGTH_SHORT).show()
            Toast.makeText(requireContext(), getString(R.string.per_grant), Toast.LENGTH_SHORT).show()
        }
        else{
            //Toast.makeText(this,getString(R.string.per_not_grant), Toast.LENGTH_SHORT).show()
            Toast.makeText(requireContext(), getString(R.string.per_not_grant), Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestPermissionDirect() {
        when {
            // Check if permission is granted
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED -> {
                // Permission is granted
                Toast.makeText(requireContext(), getString(R.string.per_grant), Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("smsto:")  // Ensures only SMS apps respond
                    putExtra("address", smsEditText.text.toString().trim()) // Set the phone number
                    putExtra("sms_body", getString(R.string.sms)) // Set the SMS body
                }
                // Start the activity using the SMS chooser
                startActivity(Intent.createChooser(intent, "SMS"))
                // Send SMS directly
                val smsManager = requireContext().getSystemService(SmsManager::class.java)
                smsManager.sendTextMessage(smsEditText.text.toString().trim(), null, "Direct SMS Message", null, null)
            }

            // Check if we should show rationale for requesting permission
            ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.SEND_SMS) -> {
                // Additional rationale is displayed
                Toast.makeText(requireContext(), getString(R.string.per_not_grant), Toast.LENGTH_SHORT).show()
                requestPermissionLauncher.launch(Manifest.permission.SEND_SMS)
            }

            // Permission has not been asked yet
            else -> {
                // Permission has not been asked yet
                Toast.makeText(requireContext(), getString(R.string.per_not_ask), Toast.LENGTH_SHORT).show()
                requestPermissionLauncher.launch(Manifest.permission.SEND_SMS)
            }
        }
    }

}