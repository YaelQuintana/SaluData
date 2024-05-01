package com.example.saludata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class loginActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var registrarseButton: Button
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton = findViewById(R.id.button2)
        registrarseButton = findViewById(R.id.button3)
        username = findViewById(R.id.editTextUsername)
        password = findViewById(R.id.editTextPassword)

        firebaseAuth = Firebase.auth

        loginButton.setOnClickListener {
            // Handle button click event here
            val userText = username.text.toString()
            val passText = password.text.toString()

            //Toast.makeText(this, "teeesssttt", Toast.LENGTH_SHORT).show()

            signIn(userText, passText)

            /*if (TextUtils.isEmpty(userText) || TextUtils.isEmpty(passText)){
                Toast.makeText(requireContext(), "Add Username & Password", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(requireContext(), "ja", Toast.LENGTH_SHORT).show()

            }*/

        }

        registrarseButton.setOnClickListener {
            val i = Intent(this, registerActivity::class.java)
            startActivity(i)
            // finish()
        }
    }

    private fun signIn(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_SHORT).show()
                    //ir a main
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                } else {
                    //Login failed
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}