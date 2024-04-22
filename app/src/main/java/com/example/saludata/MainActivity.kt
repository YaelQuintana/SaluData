package com.example.saludata

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.saludata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    //FirebaseApp.initializaApp(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.fragment_login) //R.layout.activity_main  binding.root
        //replaceFragment(Home())

        binding.buttonNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Home -> replaceFragment(Home())
                R.id.profile -> replaceFragment(Profile())
                R.id.calendar -> replaceFragment(Calendar())

                else ->{

                }


            }
            true

        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}