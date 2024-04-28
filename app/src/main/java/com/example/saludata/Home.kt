package com.example.saludata

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

class Home : Fragment() {

    private lateinit var goToChronometerButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Return the inflated view
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the button here
        goToChronometerButton = view.findViewById(R.id.goToChronometerButton)

        // Set click listener
        goToChronometerButton.setOnClickListener {
            val intent = Intent(context, ChronometerActivity::class.java)
            startActivity(intent)
        }
    }
}
