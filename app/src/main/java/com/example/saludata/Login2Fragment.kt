package com.example.saludata

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.auth


class Login2Fragment : Fragment() {

    private lateinit var loginButton: Button
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    //private lateinit var dbh: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login2, container, false)

        loginButton = view.findViewById(R.id.button2)
        username = view.findViewById(R.id.editTextUsername)
        password = view.findViewById(R.id.editTextPassword)
        //dbh = miSQLiteHelper(requireContext())
        //firebaseAuth =

        // Set click listener for loginButton


        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle login button click
        loginButton.setOnClickListener {
            // Handle button click event here
            val userText = username.text.toString()
            val passText = password.text.toString()

            Toast.makeText(this, "teeesssttt", Toast.LENGTH_SHORT).show()

            //signIn(userText, passText)

            /*if (TextUtils.isEmpty(userText) || TextUtils.isEmpty(passText)){
                Toast.makeText(requireContext(), "Add Username & Password", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(requireContext(), "ja", Toast.LENGTH_SHORT).show()

            }*/
        }
    }

    

    private fun signIn(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()){ task ->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    Toast.makeText(requireContext(), user?.uid.toString(), Toast.LENGTH_SHORT).show()
                    //ir a main
                } else {
                    //Login failed
                    Toast.makeText(
                        requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login2, container, false)
    }*/

    /*companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Login2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Login2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}

/*private fun Any.addOnCompleteListener(login2Fragment: Login2Fragment, any: Any) {
    TODO("Not yet implemented")
}*/
