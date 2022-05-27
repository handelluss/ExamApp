package com.handelluss.examapp.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.handelluss.examapp.ExerciseList
import com.handelluss.examapp.ExerciseManager
import com.handelluss.examapp.activities.R

class EmphasisExerciseFragment : Fragment(R.layout.fragment_emphasis_exercise) {

    private lateinit var manager: ExerciseManager
    private lateinit var words: ExerciseList
    private var buttonList = mutableListOf<Button>()
    private val mistakesList = mutableListOf<String>()
    private val numberOfExercise = 5
    private var currentExercise = 1
    private var basedDrawable : Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manager = ExerciseManager(requireContext())
        setFragment()

        for (button in buttonList) {
            button.setOnClickListener {
                if (button.text == words.mistakeWord) {
                    Toast.makeText(context, "ВЕРНО", Toast.LENGTH_SHORT).show()
                    //correctUpdate(button)
                }
                else {
                    Toast.makeText(context, "ОШИБКА", Toast.LENGTH_SHORT).show()
                    mistakesList.add(button.text as String)
                    //mistakeUpdate(words.mistakeWord)
                }
                currentExercise += 1
                if (currentExercise > numberOfExercise) {
                    getMistakesFragment(MistakesFragment(mistakesList))
                }
                updateFragment()

            }
        }
    }

    private fun setWordsInButtons() {
        words = manager.getExerciseList()
        for (i in 0 until buttonList.size) {
            buttonList[i].text = words.wordList[i]
        }
    }

    private fun getButtons() {
        buttonList.add(view?.findViewById<View>(R.id.firstEmphasisButton) as Button)
        buttonList.add(view?.findViewById<View>(R.id.secondEmphasisButton) as Button)
        buttonList.add(view?.findViewById<View>(R.id.thirdEmphasisButton) as Button)
        buttonList.add(view?.findViewById<View>(R.id.fourthEmphasisButton) as Button)
        buttonList.add(view?.findViewById<View>(R.id.fifthEmphasisButton) as Button)
    }

    private fun setFragment() {
        getButtons()
        setWordsInButtons()
        view?.findViewById<Chronometer>(R.id.chronometer)?.start()
        view?.findViewById<TextView>(R.id.progressTextview)?.text = "1/${numberOfExercise}"
    }

    private fun updateFragment() {
        setWordsInButtons()
        for (button in buttonList) {
            //update Background button
        }
        view?.findViewById<TextView>(R.id.progressTextview)?.text = "${currentExercise}/${numberOfExercise}"
    }

    private fun getMistakesFragment(fragment : Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentContainer, fragment)
            ?.addToBackStack("mistakes")?.commit()
    }
    /*
    private fun correctUpdate(pickedButton: Button){
        pickedButton.setBackgroundColor(pickedButton.context.getColor(R.color.green))
    }
    private fun mistakeUpdate(mistakeWord : String){
        for (button in buttonList) {
            if (button.text != mistakeWord) {
                button.setBackgroundColor(requireActivity().getColor(R.color.red))
            }
            else {
                button.setBackgroundColor(requireActivity().getColor(R.color.green))
            }
        }
    }
    */
}