package com.example.tmdb.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tmdb.R
import com.example.tmdb.data.dao.UserViewModel
import com.example.tmdb.data.dao.UserViewModelFactory
import com.example.tmdb.databinding.FragmentRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: UserViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding = registerBinding
        return registerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            toLogin()
        }
    }

    private fun toLogin() {
        if (blankInputCheck()) {
            if (passwordConfirmCheck()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.userProfile(
                        binding.etRegisterName.text.toString(),
                        "",
                        "",
                        "",
                        binding.etRegisterEmail.text.toString(),
                        binding.etRegisterPassword.text.toString(),
                        ""
                    )
                }
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                Toast.makeText(requireContext(), "Registrasi berhasil", Toast.LENGTH_LONG).show()
            } else {
                binding.tilRegisterPasswordConfirm.error =
                    getString(R.string.error_password_confirm)
            }
        } else {
            Toast.makeText(requireContext(), "Isi semua bagian!", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun passwordConfirmCheck(): Boolean {
        return binding.etRegisterPassword.text.toString() == binding.etRegisterPasswordConfirm.text.toString()
    }

    private fun blankInputCheck(): Boolean {
        return viewModel.isInputEmpty(
            binding.etRegisterName.text.toString(),
            binding.etRegisterEmail.text.toString(),
            binding.etRegisterPassword.text.toString(),
            binding.etRegisterPasswordConfirm.text.toString()
        )
    }
}