package com.irfan.challenge7.ui.register

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.irfan.challenge7.App
import com.irfan.challenge7.R
import com.irfan.challenge7.data.source.local.entity.UserEntity
import com.irfan.challenge7.databinding.FragmentRegisterBinding
import com.irfan.challenge7.ui.ViewModelFactory
import javax.inject.Inject

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding: FragmentRegisterBinding by viewBinding()

    @Inject
    lateinit var factory: ViewModelFactory

    private val registerViewModel: RegisterViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            signupButton.setOnClickListener {
                val username = binding.usernameEdt.text.toString().trim()
                val password = binding.passwordEdt.text.toString().trim()
                val confirmPassword = binding.confirmPasswordEdt.text.toString().trim()
                val email = binding.emailEdt.text.toString().trim()

                if (password != confirmPassword) {
                    Snackbar.make(it, "The confirm password doesn't match", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val user = UserEntity(
                    id = 0,
                    email = email,
                    username = username,
                    password = password
                )

                registerViewModel.registerUser(user)

                findNavController().navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment(email)
                )
            }
        }

    }
}