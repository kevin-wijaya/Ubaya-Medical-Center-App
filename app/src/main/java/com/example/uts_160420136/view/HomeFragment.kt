package com.example.uts_160420136.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.uts_160420136.R
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageUserHome = view.findViewById<ImageView>(R.id.imageUserHome)

        Picasso.get()
            .load("https://d1fdloi71mui9q.cloudfront.net/8OZ1k6UTfiCcIjl4OD4x_I3G8wuS4BY2L1U1c")
            .resize(100, 100)
            .into(imageUserHome)
    }
}