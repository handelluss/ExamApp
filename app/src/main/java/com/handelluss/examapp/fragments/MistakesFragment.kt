package com.handelluss.examapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.handelluss.examapp.activities.R

class MistakesFragment(val mistakesList : MutableList<String>) : Fragment(R.layout.fragment_mistakes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        view.findViewById<TextView>(R.id.mistakes).text = getMistakeText()


        view.findViewById<Button>(R.id.nextButton).setOnClickListener{
            activity?.supportFragmentManager?.popBackStack("exercise", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            activity?.supportFragmentManager?.popBackStack("mistakes", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun getMistakeText(): String {
        if (mistakesList.size != 0) {
            var mistakeText = ""
            for (mistake in mistakesList) {
                mistakeText += "${mistake}\n"
            }
            return mistakeText
        }
        return "Их нет :)"
    }
}