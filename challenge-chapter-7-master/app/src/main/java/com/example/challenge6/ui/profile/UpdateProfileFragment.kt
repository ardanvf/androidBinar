package com.example.challenge6.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.challenge6.R
import com.example.challenge6.data.UserApplication
import com.example.challenge6.data.UserViewModel
import com.example.challenge6.data.UserViewModelFactory
import com.example.challenge6.databinding.FragmentUpdateProfileBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateProfileFragment : Fragment() {

    private val viewModel: UserViewModel by viewModel()
    private lateinit var binding: FragmentUpdateProfileBinding
    private val args: UpdateProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val updateProfileBinding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        binding = updateProfileBinding
        return updateProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEditTextValue()

        binding.apply {
            btnUpdate.setOnClickListener {
                toProfilePage()
            }
        }
    }

    private fun toProfilePage() {
        if (binding.etUpdatePassword.text.toString() == binding.etUpdatePasswordConfirm.text.toString()) {
            lifecycleScope.launch(Dispatchers.IO) {
                val result = viewModel.userProfile(
                    args.data2?.id!!,
                    binding.etUpdateName.text.toString(),
                    binding.etUpdateEmail.text.toString(),
                    binding.etUpdatePassword.text.toString()
                )
                activity?.runOnUiThread {
                    if (result != 0) {
                        Toast.makeText(requireContext(), "Berhasil diganti!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_updateProfileFragment_to_profileFragment)
                    }
                }
            }
        }

        else {
            Toast.makeText(requireContext(), "Password tidak sesuai!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setEditTextValue() {
        binding.apply {
            etUpdateName.setText(args.data2?.name)
            etUpdateEmail.setText(args.data2?.email)
            etUpdateEmail.isEnabled = false
        }
    }
}