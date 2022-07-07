package com.example.challenge6.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.challenge6.R
import com.example.challenge6.data.UserApplication
import com.example.challenge6.data.UserViewModel
import com.example.challenge6.data.UserViewModelFactory
import com.example.challenge6.databinding.FragmentRegisterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private val viewModel: UserViewModel by viewModel()
    private lateinit var binding: FragmentRegisterBinding

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
                        binding.etRegisterEmail.text.toString(),
                        binding.etRegisterPassword.text.toString()
                    )
                }
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                Toast.makeText(requireContext(), "Registrasi berhasil", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(requireContext(), "Password tidak sesuai!", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(), "Data masih kosong!", Toast.LENGTH_LONG)
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