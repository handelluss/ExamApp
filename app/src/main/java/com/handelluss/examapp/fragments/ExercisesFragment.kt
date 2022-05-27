package com.handelluss.examapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.handelluss.examapp.activities.R

class ExercisesFragment : Fragment(R.layout.fragment_exercises){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emphasisButton = view.findViewById<View>(R.id.emphasisButton)

        emphasisButton.setOnClickListener{
            getExerciseFragment(EmphasisExerciseFragment())
        }
    }

    private fun getExerciseFragment(fragment : Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, fragment)
            ?.addToBackStack("exercise")?.commit()
    }
}