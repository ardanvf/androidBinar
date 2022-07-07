package com.example.tmdb.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tmdb.data.dao.UserViewModel
import com.example.tmdb.data.preferences.Constant
import com.example.tmdb.data.preferences.Helper
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPref: Helper

    private val viewModel: UserViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        binding = loginBinding
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStore = requireContext().createDataStore(name = "settings")
        sharedPref = Helper(requireContext())
        binding.apply {
            btnLogin.setOnClickListener {
                toHome();
            }
            tvOrOptions.setOnClickListener { toRegister() }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            val x = read("login")
            if (x == "Login") {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }

    private fun toHome() {
        if (blankInputCheck()) {
            lifecycleScope.launch(Dispatchers.IO) {
                val check = viewModel.checkUserExists(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
                if (check) {
                    activity?.runOnUiThread {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        saveSession(binding.etEmail.text.toString())
                    }
                    lifecycleScope.launch {
                        save("login", "Login")

                    }
                } else {
                    activity?.runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "Email atau Password Invalid!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        } else {
            Toast.makeText(requireContext(), "Isi semua bagian", Toast.LENGTH_SHORT).show()
        }
    }

    private fun toRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun blankInputCheck(): Boolean {
        return viewModel.isInputEmpty(
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString()
        )
    }

    private fun saveSession(email: String) {

        sharedPref.putEmail(Constant.EMAIL_USER, email)
    }

    private suspend fun save(key: String, value: String) {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private suspend fun read(key: String): String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}