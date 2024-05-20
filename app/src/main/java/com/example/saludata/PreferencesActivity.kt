package com.example.saludata

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PreferencesActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var HabitoDropdown: Spinner
    private lateinit var TiempoDropdown: Spinner
    private lateinit var confirmar_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        //val spinner: Spinner = findViewById(R.id.habito_spinner)

        HabitoDropdown = findViewById(R.id.habito_spinner)
        TiempoDropdown = findViewById(R.id.Tiempo_spinner)
        confirmar_button = findViewById(R.id.confirm_button)

        confirmar_button.setOnClickListener {
            val habito  = HabitoDropdown.selectedItem.toString()
            val Tiempo = TiempoDropdown.selectedItem.toString()

            val duration = parseTimeSelection(Tiempo)

            saveSettings(habito, duration)
        }


    }

    private fun parseTimeSelection(timeString: String): Long {
        val timeParts = timeString.split(" ")
        val timeValue = timeParts[0].toInt()
        val unit = timeParts[1]
        var multiplier = 1

        when (unit) {
            "min" -> multiplier = 60 * 1000
            "seg" -> multiplier = 1000
        }

        return (timeValue * multiplier).toLong()
    }

    private fun saveSettings(habito: String, duration: Long){
        val userID = auth.currentUser!!.uid
        val settings = hashMapOf(
            "habito" to habito,
            "duration" to duration
        )

        db.collection("habitos").document(userID)
            .set(settings)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful){
                    Log.d(
                        "RegistroHabito",
                        "Configuración de actividad registrado"
                    )
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    Log.w(
                        "RegistroHabito",
                        "Error al guardar la configuración del hábito"
                    )
                    Toast.makeText(this, "Error al registrar la configuración",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}