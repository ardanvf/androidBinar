package com.example.challenge6.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge6.data.UserApplication
import com.example.challenge6.data.UserViewModel
import com.example.challenge6.data.UserViewModelFactory
import com.example.challenge6.data.di.Injection
import com.example.challenge6.data.preferences.Constant
import com.example.challenge6.data.preferences.Helper
import com.example.challenge6.data.response.Movie
import com.example.challenge6.databinding.FragmentHomeBinding
import com.example.challenge6.ui.main.MainAdapter
import com.example.challenge6.ui.main.MainViewModel
import com.example.challenge6.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel1: MainViewModel

    private val viewModel: UserViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPref: Helper
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = homeBinding

        adapter = MainAdapter()
        val recyclerView = binding.rvMovies
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

        viewModel1 = ViewModelProvider(this, ViewModelFactory(Injection.providerRepository())).get(
            MainViewModel::class.java
        )
        viewModel1.movies.observe(requireActivity(), Observer<List<Movie>> {
            adapter.setMovies(it)
        })

        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = Helper(requireContext())

        getName()
        binding.btnProfile.setOnClickListener { toProfile() }
        doubleBackToExit()
    }

    @SuppressLint("StringFormatInvalid")
    private fun getName() {
        lifecycleScope.launch(Dispatchers.IO) {
            val name = viewModel.getUserName(sharedPref.getEmail(Constant.EMAIL_USER)!!)
            activity?.runOnUiThread {
                binding.tvWelcome.text = "Welcome, $name!"
            }
        }
    }

    private fun doubleBackToExit() {
        var doubleBackPressed: Long = 0
        val toast = Toast.makeText(requireContext(), "Tekan sekali lagi untuk keluar dari aplikasi", Toast.LENGTH_SHORT)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (doubleBackPressed + 2000 > System.currentTimeMillis()) {
                activity?.finish()
                toast.cancel()
            } else {
                toast.show()
            }
            doubleBackPressed = System.currentTimeMillis()
        }
    }

    private fun toProfile() {
        val actionToProfile = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
        findNavController().navigate(actionToProfile)
    }

    override fun onResume() {
        super.onResume()
        viewModel1.loadMovies()
    }
}