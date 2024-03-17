package com.example.saludata

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.saludata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //R.layout.activity_main
        replaceFragment(Home())

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