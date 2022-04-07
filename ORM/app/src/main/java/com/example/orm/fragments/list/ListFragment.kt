package com.example.orm.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orm.R
import com.example.orm.viewmodel.UserViewModel
import com.example.orm.databinding.FragmentListBinding

private var _binding: FragmentListBinding? = null
private val binding get() = _binding!!

class listFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_list, container, false)
        _binding = FragmentListBinding.inflate(inflater, container, false)

        //RecycleView
        val adapter = ListAdapter()
        val recyclerView = binding.RcView
        recyclerView.adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //User View Model
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })


        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment2_to_addFragment)
        }
//        return view
        return binding.root
    }
}