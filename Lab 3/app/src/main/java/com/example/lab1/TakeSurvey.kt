package com.example.lab1

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class TakeSurvey : DialogFragment() {

    private var sendMessages: ICommunicator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // send information through interface
        sendMessages = context as? ICommunicator
        val titleFromActivity = arguments?.getString("title") ?: "no value was provided"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val levels = resources.getStringArray(R.array.happiness_levels)

        val alert = AlertDialog.Builder(activity)
            .setTitle(R.string.choice)

            .setSingleChoiceItems(levels, checkedItem) { dialog, which ->
                Log.d("ConfirmationDialog", "position: $which")
                checkedItem = which
                //sendMessages?.choiceMade(which)
            }

            .setPositiveButton(android.R.string.ok) { dialog, which ->
                sendMessages?.choiceMade(checkedItem)
                Log.d("confirmation dialog", "positive button $which")

            }

            .setNegativeButton(android.R.string.cancel) { dialog, which ->
                Log.d("confirmation dialog", "negative button")
            }.create()

        return alert
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


    companion object {
        var levels = arrayOf("Level 1", "Level 2", "Level 3", "Level 4")
        var checkedItem = 1

        fun newInstance(title: Int): TakeSurvey? {
            val fragment = TakeSurvey()
            val args = Bundle()
            args.putInt("title", title)
            fragment.arguments = args
            return fragment
        }
    }

}