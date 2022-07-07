package com.irfan.challenge7.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.irfan.challenge7.App
import com.irfan.challenge7.R
import com.irfan.challenge7.databinding.FragmentLoginBinding
import com.irfan.challenge7.storage.PreferencesManager.UserLoggedIn
import com.irfan.challenge7.ui.ViewModelFactory
import com.irfan.challenge7.utils.showSnackBar
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()

    private val args: LoginFragmentArgs by navArgs()

    @Inject lateinit var factory: ViewModelFactory

    private val loginViewModel: LoginViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            emailEdt.setText(args.email)
            signupTv.setOnClickListener {
                findNavController().navigate(
                    R.id.action_loginFragment_to_registerFragment
                )
            }

            loginButton.setOnClickListener { view ->
                val email = emailEdt.text.toString().trim()
                val password = passwordEdt.text.toString().trim()
                doLogin(email, password, view)
            }
        }
    }

    private fun doLogin(email: String, password: String, view: View) {
        loginViewModel.doLogin(email, password).observe(viewLifecycleOwner) {
            if (it != null) {
                loginViewModel.setIsAuthorized(it, true)

                val userLoggedIn = UserLoggedIn(true, email)
                loginViewModel.setUserLoggedIn(userLoggedIn)

                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment(it)
                )
            } else {
                view.showSnackBar("Login failed!")
            }
        }
    }
}