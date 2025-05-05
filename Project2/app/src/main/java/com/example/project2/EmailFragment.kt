package com.example.project2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class EmailFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var sendEmailButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment's layout
        val view = inflater.inflate(R.layout.fragment_email, container, false)

        // Initialize views
        emailEditText = view.findViewById(R.id.emailEditText)
        sendEmailButton = view.findViewById(R.id.sendEmailButton)

        // Set the button click listener
        sendEmailButton.setOnClickListener {
            val emailAddress = emailEditText.text.toString().trim()

            if (emailAddress.isNotEmpty()) {
                sendEmail(emailAddress)
            } else {
                // Optionally, show an error message if email is empty
                emailEditText.error = "Please enter a valid email address"
            }
        }

        return view
    }

    private fun sendEmail(emailAddress: String) {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822" // MIME type for email
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
            putExtra(Intent.EXTRA_SUBJECT, "Subject goes here")
            putExtra(Intent.EXTRA_TEXT, "Email body goes here")
        }

        // Log the intent to check its content
        Log.d("EmailFragment", "Intent: $emailIntent")

        // Check if there's an email app that can handle the intent
        val packageManager = requireActivity().packageManager
        if (emailIntent.resolveActivity(packageManager) != null) {
            Log.d("EmailFragment", "Email app found, launching intent")
            startActivity(emailIntent)
        } else {
            Log.d("EmailFragment", "No email app found to handle intent")
            // Handle the case when no email app is available
            // You can show a Toast or a dialog here
        }
    }


//    private fun handleButtonPress() {
//        button.setOnClickListener {
//            Log.i("FirstFragment", "Button pressed")
//            val activityButton: Button? =
//                activity?.findViewById(R.id.btnReplaceFragment)
//            activityButton?.text = "dhvfhjs"
//        }
//    }
}