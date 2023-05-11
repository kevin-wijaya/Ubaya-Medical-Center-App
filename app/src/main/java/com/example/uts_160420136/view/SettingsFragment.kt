package com.example.uts_160420136.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.uts_160420136.R
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.UserViewModel

class SettingsFragment : Fragment() {
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = SettingsFragmentArgs.fromBundle(requireArguments()).userId.toString()

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.load(id)

        observeViewModel(view)
    }

    fun observeViewModel(view: View) {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            with(view) {
                findViewById<ImageView>(R.id.imageUserSettings).loadImage(it.photoUrl.toString(), findViewById(R.id.progressSettingsBar))

                findViewById<EditText>(R.id.editTextNameProfile).setText(it.name)
                findViewById<EditText>(R.id.editTextUserNameProfile).setText(it.username)
                findViewById<EditText>(R.id.editTextPasswordProfile).setText(it.password)
                findViewById<EditText>(R.id.editTextGmail).setText(it.gmail)
                findViewById<EditText>(R.id.editTextBODProfile).setText(it.bod)
                findViewById<EditText>(R.id.editTextNumberPhone).setText(it.numberPhone)
                findViewById<EditText>(R.id.editTextAddress).setText(it.address)
                findViewById<EditText>(R.id.editTextBloodType).setText(it.bloodType)

                findViewById<Button>(R.id.buttonSave).setOnClickListener {
                    Toast.makeText(activity, "Saved", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}