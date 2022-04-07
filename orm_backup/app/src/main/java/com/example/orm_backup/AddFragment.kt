package com.example.orm_backup

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
import com.example.orm_backup.R
import com.example.orm_backup.data.User
import com.example.orm_backup.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_add.view.btnAdd

class addFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = return inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val age = editTextAge.text

        if (inputCheck(name, email, age)) {
            //Tambah User
            val user = User(0, name, email, Integer.parseInt(age.toString()))
            //Menambah ke database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
            //Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment2)
        } else {
            Toast.makeText(requireContext(), "Gagal Menambahkan Data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, email: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && age.isEmpty())
    }
}
