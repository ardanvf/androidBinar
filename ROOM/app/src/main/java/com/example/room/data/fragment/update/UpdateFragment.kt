package com.example.room.data.fragment.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.example.room.R
import com.example.room.data.User
import com.example.room.data.UserViewModel
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateTextName.setText(args.currentUser.name)
        view.updateTextEmail.setText(args.currentUser.email)
        view.updateTextAge.setText(args.currentUser.age.toString())

        view.btnUpdate.setOnClickListener{
            updateItem()
        }

        view.btnDelete.setOnClickListener{
            deleteUser()
        }

        setHasOptionsMenu(true)
        
        return view
    }

    private fun updateItem(){
        val name = updateTextName.text.toString()
        val email = updateTextEmail.text.toString()
        val age = Integer.parseInt(updateTextAge.text.toString())

        if (inputCheck(name,email, updateTextAge.text.toString())){
            val updateUser = User(args.currentUser.id, name, email, age)
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Data Berhasil diPerbarui", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Isi Dengan Benar!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, email: String, age: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && age.isEmpty())
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_ ->
            builder.setTitle("Delete ${args.currentUser.name}?")
            builder.setMessage("data ${args.currentUser.name} akan dihapus, Yakin?")
            builder.create().show()
        }
    }

}