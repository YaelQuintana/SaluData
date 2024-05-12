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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Profile : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var imageView2: ImageView
    private lateinit var logout: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var textview: TextView
    private lateinit var getUser: String

    //bottom sheet
    private lateinit var button_Bienvenidos: Button

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

        imageView2 = view.findViewById(R.id.imageView2)
        imageView2.setClipToOutline(true)

        textview = view.findViewById(R.id.textView)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        fetchUserData()


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


}