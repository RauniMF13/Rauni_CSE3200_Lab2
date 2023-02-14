package com.cse3200.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.cse3200.lab1.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBindings: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainBindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBindings.root)

        activityMainBindings.trueButton.setOnClickListener {
            checkAnswer(true)
            updateQuestion()
        }

        activityMainBindings.falseButton.setOnClickListener {
            checkAnswer(false)
            updateQuestion()
        }

        activityMainBindings.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        activityMainBindings.prevButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex = (currentIndex - 1)
                updateQuestion()
            }
        }

        updateQuestion()
    }

    private val questionBank = listOf(
        Question(R.string.question_australia, answer = true, firstGuess = true),
        Question(R.string.question_oceans, answer = true, firstGuess = true),
        Question(R.string.question_mideast, answer = false, firstGuess = true),
        Question(R.string.question_africa, answer = false, firstGuess = true),
        Question(R.string.question_americas, answer = true, firstGuess = true),
        Question(R.string.question_asia, answer = true, firstGuess = true)
    )

    private var currentIndex = 0

    private var score = Score(0)

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        activityMainBindings.textView2.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        if (messageResId == R.string.incorrect_toast) {
            questionBank[currentIndex].firstGuess = false
        }

        if (messageResId == R.string.correct_toast) {
            if (questionBank[currentIndex].firstGuess) {
                score.curScore = score.curScore + 1
            }
            questionBank[currentIndex].firstGuess = false
            currentIndex = (currentIndex + 1) % questionBank.size
        }

        Toast.makeText(
            this,
            messageResId,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
}