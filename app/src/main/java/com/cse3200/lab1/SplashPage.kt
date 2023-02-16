package com.cse3200.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cse3200.lab1.databinding.ActivitySplashPageBinding

class SplashPage : AppCompatActivity() {

    private lateinit var splashPageBinding: ActivitySplashPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash_page)

        splashPageBinding = ActivitySplashPageBinding.inflate(layoutInflater)
        setContentView(splashPageBinding.root)

        splashPageBinding.launchButton.setOnClickListener {
            Intent(this, MainActivity::class.java).also { startActivity(it)}

        }
    }
}