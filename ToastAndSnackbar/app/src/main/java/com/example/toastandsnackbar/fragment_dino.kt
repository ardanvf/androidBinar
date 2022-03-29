package com.example.toastandsnackbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.toastandsnackbar.databinding.ActivityMainBinding
import com.example.toastandsnackbar.databinding.FragmentDinoBinding
import com.google.android.material.snackbar.Snackbar

private var _binding: FragmentDinoBinding? = null
private val binding get() = _binding!!
class fragment_dino : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDinoBinding.inflate(inflater,container, false)

        val myName = binding.namakuu.text.toString()
        binding.cancelBtn.setOnClickListener{
            dismiss()
        }
        binding.loginBtn.setOnClickListener{
            Toast.makeText(context, "Hallo $myName", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return binding.root
    }

//    override fun onResume() {
//        super.onResume()
//        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
//    }
}