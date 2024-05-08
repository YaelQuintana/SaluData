package com.example.saludata

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class registerActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var confirmPasswordEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializa Firebase Auth y Firestore
        auth = FirebaseAuth.getInstance()
        db =  FirebaseFirestore.getInstance() /*Firebase.firestore*/

        // Obtiene referencias a los campos de entrada de la interfaz de usuario
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        usernameEditText = findViewById(R.id.usernameEditText)
        registerButton = findViewById(R.id.registerButton)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)

        // Maneja el clic del botón de registro
        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val username = usernameEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()


            val validationResult = when {
                email.isEmpty() || password.isEmpty() || username.isEmpty() -> {
                    "Please fill out all fields" // Return a string indicating empty fields
                }
                password != confirmPassword -> {
                    "Passwords do not match" // Return a string indicating password mismatch
                }
                else -> {
                    null // No errors, return null (or any other indicator of success)
                }
            }

            if (validationResult != null) {
                Toast.makeText(this, validationResult, Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Early return to avoid signup call if there are errors
            }

// All fields are valid, proceed with signup
            signUp(email, password, username)

        }
    }
        // Registra al usuario en Firebase Authentication

    private fun signUp(email: String, password: String, username: String) {
        //val db = Firebase.firestore

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                        // El registro fue exitoso, guarda los datos del usuario en Firestore
                    val userId = auth.currentUser!!.uid
                    val user = hashMapOf(
                        "username" to username,
                        "email" to email
                    )
                    db.collection("usuarios").document(userId)
                        .set(user)
                        .addOnCompleteListener(this) { task2 ->
                            if (task2.isSuccessful) {
                                // El usuario se guardó en Firestore, inicia la actividad principal
                                Log.d(
                                    "RegistroActivity",
                                    "Usuario registrado y guardado en Firestore"
                                )
                                startActivity(Intent(this, PreferencesActivity::class.java))
                                finish()
                            } else {
                                // Error al guardar al usuario en Firestore
                                Log.w(
                                    "RegistroActivity",
                                    "Error al guardar al usuario en Firestore",
                                    task2.exception
                                )
                                Toast.makeText(this, "Error al registrarse en firestore", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    // El registro falló
                    Log.w("RegistroActivity", "Error al registrar al usuario", task.exception)
                    Toast.makeText(this, "Error al registrarse", Toast.LENGTH_SHORT).show()
                }

            }


    }




}
