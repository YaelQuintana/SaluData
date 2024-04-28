package com.example.saludata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer


class ChronometerFragment : Fragment() {

    private lateinit var chronometer: Chronometer
    private lateinit var controlButton: Button
    private var isRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chronometer, container, false)

        // Inicializar cronometro
        chronometer = view.findViewById(R.id.chronometer)
        controlButton = view.findViewById(R.id.floatingActionButton)

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


        return view
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