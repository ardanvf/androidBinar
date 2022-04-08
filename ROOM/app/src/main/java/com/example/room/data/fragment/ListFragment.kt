package com.example.room.data.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import androidx.navigation.fragment.navArgs
import com.example.room.data.fragment.ListFragment
import com.example.room.data.fragment.update.UpdateFragmentArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //Recycler View
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //user view model
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        view.floatingActionButton.setOnClickListener{
            val customLayout = inflater.inflate(R.layout.fragment_add, null)
            val mBuilder = MaterialAlertDialogBuilder(requireContext())
                .setView(customLayout)
                .setTitle("Add Data Form")
                .show()
            mBuilder

            //findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }
}