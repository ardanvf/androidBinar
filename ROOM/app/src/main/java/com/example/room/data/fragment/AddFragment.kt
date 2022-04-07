package com.example.room.data.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.data.User
import com.example.room.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.btnAdd.setOnClickListener{
             insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
//        val age = editTextAge.text
        val age = 99

        if (inputCheck(name,email, age.toString())){
            val user = User(0, name, email, Integer.parseInt(age.toString()))
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Data Gagal Ditambahkan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, email: String, age: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && age.isEmpty())
    }
    
}