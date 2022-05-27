package com.handelluss.examapp

import android.content.Context
import com.handelluss.examapp.activities.R

class ExerciseManager(context: Context) {

    private val emphasisFileStream = context.resources.openRawResource(R.raw.emphasis)
    private val emphasisMistakesFileStream = context.resources.openRawResource(R.raw.mistakes)
    private var wordList = mutableListOf<String>()
    private var mistakesList = mutableListOf<String>()

    private val wordsInExercise = 5
    private var currentWord = 0

    init {
        for (line in emphasisFileStream.bufferedReader().readLines()) {
            wordList.add(line)
        }
        for (line in emphasisMistakesFileStream.bufferedReader().readLines()) {
            mistakesList.add(line)
        }
        wordList.shuffle()
        mistakesList.shuffle()
    }

    fun getExerciseList(): ExerciseList {
        currentWord += wordsInExercise
        val resultList = wordList.subList(currentWord - 5, currentWord)
        resultList[4] = mistakesList[currentWord / wordsInExercise]
        val mistake = resultList[4]
        resultList.shuffle()
        return ExerciseList(resultList, mistake)
    }
}