package com.example.saludata

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ChronometerActivity : AppCompatActivity() {

    private lateinit var chronometer: Chronometer
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var controlButton: FloatingActionButton
    private var isRunning: Boolean = false
    private lateinit var chronometerData: ChronometerData
    private var pauseOffset: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chronometer)

        chronometer = findViewById(R.id.chronometer)
        controlButton = findViewById(R.id.floatingActionButton)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()


        fetchUserData()

        controlButton.setOnClickListener {
            if (isRunning) {
                chronometer.stop()
                pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
                isRunning = false
                //chronometer.base = SystemClock.elapsedRealtime()
            } else {
                chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
                chronometer.start()
                isRunning = true
            }
        }

        // Click listener
        /*controlButton.setOnClickListener {
            if (::chronometerData.isInitialized && chronometerData.duration > 0){
            //chronometer.start()
            startChronometer(chronometerData.duration)
            isRunning = true



            } else if (isRunning) {
                chronometer.stop()
                isRunning = false
            } else {
                Log.w("ChronometerActivity", "Chronometer data not available")
                Toast.makeText(this, "Data not available", Toast.LENGTH_SHORT).show()
            }
        }*/



    }

    private fun fetchUserData() {
        val userId = auth.currentUser!!.uid
        val docRef = db.collection("habitos").document(userId)
        //val chronometerData: Long

        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val duration = document.getLong("duration")!!
                    startChronometer(duration)
                    //chronometerData.duration = duration

                } else {
                    Log.w("Chronometer activity", "User data not found")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("ChronometerActivity", "Firestore got an error", exception)
            }
    }

    private fun startChronometer(duration: Long) {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
        isRunning = true

        chronometer.setOnChronometerTickListener {
            val elapsedTime = SystemClock.elapsedRealtime() - chronometer.base
            if (elapsedTime >= duration) {
                chronometer.stop()
                isRunning = false
            }
        }
    }


    data class ChronometerData (var duration: Long = 0)
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