package com.cse3200.lab1

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean, var firstGuess: Boolean)