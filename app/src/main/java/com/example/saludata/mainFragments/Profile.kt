package com.example.saludata.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.saludata.R

class Profile : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var imageView2: ImageView

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

        imageView2 = view.findViewById(R.id.imageView2)
        imageView2.setClipToOutline(true)
       // return inflater.inflate(R.layout.fragment_profile, container, false)

        return view
    }

    /*override fun showBottomSheet() {

    }*/


}