package com.example.saludata

import android.os.Bundle
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChronometerActivity : AppCompatActivity() {

    private lateinit var chronometer: Chronometer
    private lateinit var controlButton: Button
    private var isRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chronometer)

        chronometer = findViewById(R.id.chronometer)
        controlButton = findViewById(R.id.floatingActionButton)

        // Click listener
        controlButton.setOnClickListener {
            if (isRunning) {
                chronometer.stop()
                isRunning = false

            } else {
                chronometer.start()
                isRunning = true

            }
        }



    }

    override fun onResume() {
        super.onResume()
        if (isRunning) {
            chronometer.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (isRunning) {
            chronometer.stop()
        }
    }

}