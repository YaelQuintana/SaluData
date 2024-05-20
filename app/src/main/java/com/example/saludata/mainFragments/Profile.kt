package com.example.saludata.mainFragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.saludata.MainActivity
import com.example.saludata.R
import com.example.saludata.loginActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Profile : Fragment() {
    // Inicialización de variables
    private lateinit var imageView2: ImageView
    private lateinit var logout: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var textview: TextView
    private lateinit var getUser: String
    private lateinit var dialog: BottomSheetDialog

    //bottom sheet buttons
    private lateinit var button_Bienvenidos: Button
    private lateinit var button_Nosostros: Button

    private lateinit var button_Servicios: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        //textview = getUser
        // inicializar conexión a la base de datos
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Obtener datos de usuario
        fetchUserData()

        imageView2 = view.findViewById(R.id.imageView2)
        imageView2.setClipToOutline(true)

        textview = view.findViewById(R.id.textView)



        // Botones del bottom sheet
        button_Bienvenidos = view.findViewById(R.id.button_Bienvenidos)

        button_Bienvenidos.setOnClickListener {
            val id = "Bienvenidos"
            showDialog(id)
        }

        button_Nosostros = view.findViewById(R.id.button_Nosostros)

        button_Nosostros.setOnClickListener {
            val id = "Nosotros"
            showDialog(id)
        }

        button_Servicios = view.findViewById(R.id.button_Servicios)

        button_Servicios.setOnClickListener {
            val id = "Servicios"
            showDialog(id)
        }


        // Cerrar sesión
        logout = view.findViewById(R.id.button_cerrarSesion)

        logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(activity, loginActivity::class.java))
            activity?.finish()
        }
       // return inflater.inflate(R.layout.fragment_profile, container, false)

        return view
    }



    /*override fun showBottomSheet() {

    }*/

    // Función para obtener los datos del usuario
    private fun fetchUserData() {
        val userId = auth.currentUser!!.uid
        val docRef = db.collection("usuarios").document(userId)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    getUser = document.getString("username").toString()
                    textview.text = getUser
                } else {
                    Log.w("Profile activity", "User data not found")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("ChronometerActivity", "Firestore got an error", exception)
            }
    }

    private fun showDialog(id: String) {
        val fragmentActivity = activity ?: return
        val dialogView = layoutInflater.inflate(when (id) {
            "Bienvenidos" -> R.layout.fragment_bottom_sheet__informativa
            "Nosotros" -> R.layout.sobre_nosotros
            "Servicios" -> R.layout.nuestros_servicios
            else -> return // Use a default layout
        }, null)


        dialog = BottomSheetDialog(fragmentActivity, R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        dialog.show()
    }

}